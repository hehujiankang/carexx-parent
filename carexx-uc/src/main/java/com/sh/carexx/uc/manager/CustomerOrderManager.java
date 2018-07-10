package com.sh.carexx.uc.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.bean.order.ConfirmCompletedOrderFormBean;
import com.sh.carexx.bean.order.CustomerAppointOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderAdjustAmtFormBean;
import com.sh.carexx.bean.order.CustomerOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderQueryFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.TimeUnit;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.enums.order.OrderScheduleStatus;
import com.sh.carexx.common.enums.order.OrderSettleStatus;
import com.sh.carexx.common.enums.order.OrderStatus;
import com.sh.carexx.common.enums.order.OrderType;
import com.sh.carexx.common.enums.order.ProofType;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.keygen.KeyGenerator;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.CustomerOrder;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.InstCareService;
import com.sh.carexx.model.uc.InstCustomer;
import com.sh.carexx.model.uc.InstHoliday;
import com.sh.carexx.model.uc.UserInfo;
import com.sh.carexx.uc.service.CustomerOrderScheduleService;
import com.sh.carexx.uc.service.CustomerOrderService;
import com.sh.carexx.uc.service.InstCareServiceService;
import com.sh.carexx.uc.service.InstCustomerService;
import com.sh.carexx.uc.service.InstHolidayService;
import com.sh.carexx.uc.service.InstSettleService;
import com.sh.carexx.uc.service.OrderSettleService;
import com.sh.carexx.uc.service.UserService;

/**
 * 
 * ClassName: CustomerOrderManager <br/>
 * Function: 客户订单 <br/>
 * Date: 2018年5月29日 下午5:29:57 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class CustomerOrderManager {
	@Autowired
	private KeyGenerator keyGenerator;
	@Autowired
	private CustomerOrderService customerOrderService;
	@Autowired
	private OrderPaymentManager orderPaymentManager;
	@Autowired
	private InstCareServiceService instCareServiceService;
	@Autowired
	private UserService userService;
	@Autowired
	private InstCustomerService instCustomerService;
	@Autowired
	private CustomerOrderScheduleService customerOrderScheduleService;
	@Autowired
	private InstHolidayService instHolidayService;
	@Autowired
	private OrderSettleService orderSettleService;
	@Autowired
	private InstSettleService instSettleService;

	/**
	 * 
	 * calcServiceFee:(计算订单金额). <br/>
	 * 
	 * @author hetao
	 * @param instId：机构id
	 * @param serviceId：服务id
	 * @param serviceStartTime：服务开始时间
	 * @param serviceEndTime：服务结束时间
	 * @return
	 * @since JDK 1.8
	 */
	public BigDecimal calcServiceFee(Integer instId, Integer serviceId, Date serviceStartTime, Date serviceEndTime) {
		int hour = DateUtils.getHourDiff(serviceStartTime, serviceEndTime);
		int holidayHour = 0;
		int checkHour = 0;
		Date holidayStartTime = serviceStartTime;
		while (checkHour < hour) {
			InstHoliday instHoliday = this.instHolidayService.getByScheduleDate(instId, holidayStartTime);
			if (instHoliday != null) {
				holidayHour += 12;
			}
			holidayStartTime = DateUtils.addHour(holidayStartTime, 12);
			checkHour += 12;
		}
		InstCareService instCareService = instCareServiceService.queryServicePrice(instId, serviceId);
		BigDecimal normalServiceFeeAmt = BigDecimal.ZERO;
		BigDecimal holidayServiceFeeAmt = BigDecimal.ZERO;
		if (TimeUnit.DAY.getValue() == instCareService.getServiceUnit()) {
			if (holidayHour > 0) {
				holidayServiceFeeAmt = instCareService.getServicePrice().multiply(
						new BigDecimal(holidayHour * 2).divide(new BigDecimal(24), 2, BigDecimal.ROUND_HALF_UP));
			}
			if (hour - holidayHour > 0) {
				normalServiceFeeAmt = instCareService.getServicePrice().multiply(
						new BigDecimal(hour - holidayHour).divide(new BigDecimal(24), 2, BigDecimal.ROUND_HALF_UP));
			}
		}
		return normalServiceFeeAmt.add(holidayServiceFeeAmt).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * add:(客户端代客下单). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void add(CustomerOrderFormBean customerOrderFormBean) throws BizException {
		Date serviceStartTime = null;
		Date serviceEndTime = null;
		if (ValidUtils.isDateTime(customerOrderFormBean.getServiceStartTime())) {
			serviceStartTime = DateUtils.toDate(customerOrderFormBean.getServiceStartTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS);
		}
		if (ValidUtils.isDateTime(customerOrderFormBean.getServiceEndTime())) {
			serviceEndTime = DateUtils.toDate(customerOrderFormBean.getServiceEndTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
		}
		if (!serviceStartTime.before(serviceEndTime)) {
			throw new BizException(ErrorCode.TIME_END_BEFORE_START_ERROR);
		}
		Date da = this.instSettleService.queryMaxSettleDateBySettleStatus(customerOrderFormBean.getInstId());
		if (da != null) {
			if (!da.before(serviceStartTime)) {
				throw new BizException(ErrorCode.ORDER_SETTLE_EXISTS_ERROR);
			}
		}
		CustomerOrderQueryFormBean customerOrderQueryFormBean = new CustomerOrderQueryFormBean();
		customerOrderQueryFormBean.setInstId(customerOrderFormBean.getInstId());
		customerOrderQueryFormBean.setRealName(customerOrderFormBean.getPatientName());
		customerOrderQueryFormBean.setServiceId(customerOrderFormBean.getServiceId().toString());
		customerOrderQueryFormBean.setServiceStartTime(customerOrderFormBean.getServiceStartTime());
		customerOrderQueryFormBean.setServiceEndTime(customerOrderFormBean.getServiceEndTime());
		List<Map<?, ?>> orderlist = this.customerOrderService.queryOrderExistence(customerOrderQueryFormBean);
		if (orderlist.size() > 0) {
			throw new BizException(ErrorCode.CUSTOMER_ORDER_SCHEDULE_EXISTS_ERROR);
		}

		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrderType(OrderType.UNDERLINE_ORDER.getValue());
		customerOrder.setInstId(customerOrderFormBean.getInstId());
		customerOrder.setCustomerId(customerOrderFormBean.getCustomerId());
		customerOrder.setServiceId(customerOrderFormBean.getServiceId());
		String orderNo = this.keyGenerator.generateOrderNo();
		customerOrder.setOrderNo(orderNo);
		customerOrder.setInpatientAreaId(customerOrderFormBean.getInpatientAreaId());
		customerOrder.setInpatientWard(customerOrderFormBean.getInpatientWard());
		customerOrder.setServiceStartTime(serviceStartTime);
		customerOrder.setServiceEndTime(serviceEndTime);
		customerOrder.setOrderAmt(this.calcServiceFee(customerOrder.getInstId(), customerOrder.getServiceId(),
				customerOrder.getServiceStartTime(), customerOrder.getServiceEndTime()));
		BigDecimal servicePrice = instCareServiceService
				.queryServicePrice(customerOrder.getInstId(), customerOrder.getServiceId()).getServicePrice();
		BigDecimal holiday = customerOrder.getOrderAmt().divide(servicePrice, 1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
		customerOrder.setHoliday(holiday);
		customerOrder.setAdjustAmt(new BigDecimal(0));
		customerOrder.setOperator(customerOrderFormBean.getOperator());
		customerOrder.setOrderRemark(customerOrderFormBean.getOrderRemark());
		customerOrder.setOrderStatus(OrderStatus.WAIT_SCHEDULE.getValue());
		this.customerOrderService.save(customerOrder);

		this.orderPaymentManager.add(customerOrder);
	}

	/**
	 * 
	 * addAppointOrder:(移动端下单). <br/>
	 * 
	 * @author hetao
	 * @param customerAppointOrderFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void addAppointOrder(CustomerAppointOrderFormBean customerAppointOrderFormBean) throws BizException {
		Date serviceStartTime = null;
		Date serviceEndTime = null;
		if (ValidUtils.isDateTime(customerAppointOrderFormBean.getServiceStartTime())) {
			serviceStartTime = DateUtils.toDate(customerAppointOrderFormBean.getServiceStartTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS);
		}
		if (ValidUtils.isDateTime(customerAppointOrderFormBean.getServiceEndTime())) {
			serviceEndTime = DateUtils.toDate(customerAppointOrderFormBean.getServiceEndTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS);
		}
		if (!serviceStartTime.before(serviceEndTime)) {
			throw new BizException(ErrorCode.TIME_END_BEFORE_START_ERROR);
		}
		Date da = this.instSettleService.queryMaxSettleDateBySettleStatus(customerAppointOrderFormBean.getInstId());
		if (da != null) {
			if (!da.before(serviceStartTime)) {
				throw new BizException(ErrorCode.ORDER_SETTLE_EXISTS_ERROR);
			}
		}

		CustomerOrderQueryFormBean customerOrderQueryFormBean = new CustomerOrderQueryFormBean();
		customerOrderQueryFormBean.setInstId(customerAppointOrderFormBean.getInstId());
		customerOrderQueryFormBean.setRealName(customerAppointOrderFormBean.getPatientName());
		customerOrderQueryFormBean.setServiceId(customerAppointOrderFormBean.getServiceId().toString());
		customerOrderQueryFormBean.setServiceStartTime(customerAppointOrderFormBean.getServiceStartTime());
		customerOrderQueryFormBean.setServiceEndTime(customerAppointOrderFormBean.getServiceEndTime());
		List<Map<?, ?>> orderlist = this.customerOrderService.queryOrderExistence(customerOrderQueryFormBean);
		if (orderlist.size() > 0) {
			throw new BizException(ErrorCode.CUSTOMER_ORDER_SCHEDULE_EXISTS_ERROR);
		}

		Integer customerId = 0;
		UserInfo userInfo = this.userService.getById(customerAppointOrderFormBean.getCustomerId());
		InstCustomer instCustomer = this.instCustomerService.queryCustomerExistence(
				customerAppointOrderFormBean.getInstId(), customerAppointOrderFormBean.getCustomerId(),
				customerAppointOrderFormBean.getPatientName());
		if (instCustomer == null) {
			instCustomer = new InstCustomer();
			instCustomer.setInstId(customerAppointOrderFormBean.getInstId());
			instCustomer.setUserId(customerAppointOrderFormBean.getCustomerId());
			instCustomer.setRealName(customerAppointOrderFormBean.getPatientName());
			instCustomer.setPhone(userInfo.getMobile());
			instCustomer.setSex(userInfo.getSex());
			instCustomer.setAddress(userInfo.getRegion());
			instCustomer.setCustomerStatus(UseStatus.ENABLED.getValue());
			this.instCustomerService.save(instCustomer);
			customerId = instCustomer.getId();
		} else {
			customerId = instCustomer.getId();
		}

		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrderType(OrderType.ONLINE_ORDER.getValue());
		customerOrder.setInstId(customerAppointOrderFormBean.getInstId());
		customerOrder.setUserId(customerAppointOrderFormBean.getCustomerId());
		customerOrder.setCustomerId(customerId);
		customerOrder.setServiceId(customerAppointOrderFormBean.getServiceId());
		String orderNo = this.keyGenerator.generateOrderNo();
		customerOrder.setOrderNo(orderNo);
		customerOrder.setInpatientAreaId(customerAppointOrderFormBean.getInpatientAreaId());
		customerOrder.setInpatientWard(customerAppointOrderFormBean.getInpatientWard());
		customerOrder.setServiceStartTime(serviceStartTime);
		customerOrder.setServiceEndTime(serviceEndTime);
		customerOrder.setOrderAmt(this.calcServiceFee(customerOrder.getInstId(), customerOrder.getServiceId(),
				customerOrder.getServiceStartTime(), customerOrder.getServiceEndTime()));
		BigDecimal servicePrice = instCareServiceService
				.queryServicePrice(customerOrder.getInstId(), customerOrder.getServiceId()).getServicePrice();
		BigDecimal holiday = customerOrder.getOrderAmt().divide(servicePrice, 1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
		customerOrder.setHoliday(holiday);
		customerOrder.setAdjustAmt(new BigDecimal(0));
		customerOrder.setOrderStatus(OrderStatus.WAIT_SCHEDULE.getValue());
		customerOrder.setOrderRemark(customerAppointOrderFormBean.getOrderRemark());

		this.customerOrderService.save(customerOrder);
		this.orderPaymentManager.add(customerOrder);
	}

	/**
	 * 
	 * cancel:(取消订单). <br/>
	 * 
	 * @author hetao
	 * @param orderNo：订单号
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void cancel(String orderNo) throws BizException {
		this.customerOrderService.updateOrderCancel(orderNo, OrderStatus.CANCELED.getValue());
		List<CustomerOrderSchedule> orderschedulelist = this.customerOrderScheduleService.getByOrderNo(orderNo);
		for (CustomerOrderSchedule list : orderschedulelist) {
			this.customerOrderScheduleService.deleteOrderSchedule(list.getId(), OrderScheduleStatus.DELETED.getValue());
			this.orderSettleService.updateStatus(list.getId(), UseStatus.ENABLED.getValue(),
					UseStatus.DISABLED.getValue());
		}
	}

	/**
	 * 
	 * confirmCompleted:(已支付订单). <br/>
	 * 
	 * @author hetao
	 * @param orderNo：订单号
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void throughPay(String orderNo) throws BizException {
		List<CustomerOrderSchedule> orderScheduleList = this.customerOrderScheduleService.getByOrderNo(orderNo);
		for (CustomerOrderSchedule orderSchedule : orderScheduleList) {
			this.orderSettleService.updateStatus(orderSchedule.getId(), OrderSettleStatus.SETTLING.getValue(),
					OrderSettleStatus.SETTLED.getValue());
		}
		this.orderPaymentManager.offlinePayment(orderNo);
		this.customerOrderService.updateStatus(orderNo, OrderStatus.WAIT_PAY.getValue(),
				OrderStatus.ALREADY_PAY.getValue());
	}

	/**
	 * confirmCompleted:(确认完成订单). <br/>
	 * 
	 * @author hetao
	 * @param confirmCompletedOrderFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void confirmCompleted(ConfirmCompletedOrderFormBean confirmCompletedOrderFormBean) throws BizException {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrderNo(confirmCompletedOrderFormBean.getOrderNo());
		customerOrder.setInstSysId(confirmCompletedOrderFormBean.getInstSysId());
		Byte proofType = confirmCompletedOrderFormBean.getProofType();
		customerOrder.setProofType(proofType);
		if (proofType == 1) {
			customerOrder.setReceiptNo(confirmCompletedOrderFormBean.getReceiptOrInvoice());
		} else if (proofType == 2) {
			customerOrder.setInvoiceNo(confirmCompletedOrderFormBean.getReceiptOrInvoice());
		}
		customerOrder.setSigningPerson(confirmCompletedOrderFormBean.getSigningPerson());
		this.customerOrderService.confirmCompleted(customerOrder);
		this.customerOrderService.updateStatus(confirmCompletedOrderFormBean.getOrderNo(),
				OrderStatus.ALREADY_PAY.getValue(), OrderStatus.COMPLETED.getValue());
	}

	/**
	 * 
	 * adjustAmt:(调整订单金额). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderAdjustAmtFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void adjustAmt(CustomerOrderAdjustAmtFormBean customerOrderAdjustAmtFormBean) throws BizException {
		BigDecimal adjustAmt = new BigDecimal(customerOrderAdjustAmtFormBean.getAdjustAmt());
		CustomerOrder customerOrder = this.customerOrderService
				.getByOrderNo(customerOrderAdjustAmtFormBean.getOrderNo());
		if (customerOrder.getOrderAmt().add(adjustAmt).compareTo(BigDecimal.ZERO) < 1) {
			throw new BizException(ErrorCode.ADJUST_AMT_GRERTER_ORDER_AMT_ERROR);
		}
		customerOrder.setAdjustAmt(adjustAmt);
		this.customerOrderService.updateAdjustAmt(customerOrder);
	}

	public void modifyReceiptOrInvoice(ConfirmCompletedOrderFormBean confirmCompletedOrderFormBean)
			throws BizException {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrderNo(confirmCompletedOrderFormBean.getOrderNo());
		customerOrder.setProofType(confirmCompletedOrderFormBean.getProofType());
		if (confirmCompletedOrderFormBean.getProofType() == ProofType.RECEIPT.getValue()) {
			customerOrder.setReceiptNo(confirmCompletedOrderFormBean.getReceiptOrInvoice());
		} else if (confirmCompletedOrderFormBean.getProofType() == ProofType.INVOICE.getValue()) {
			customerOrder.setInvoiceNo(confirmCompletedOrderFormBean.getReceiptOrInvoice());
		}

		this.customerOrderService.updateReceiptOrInvoice(customerOrder);
	}

}
