package com.sh.carexx.uc.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.bean.order.CustomerOrderScheduleFormBean;
import com.sh.carexx.bean.order.OrderSettleAdjustAmtFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.order.OrderScheduleStatus;
import com.sh.carexx.common.enums.order.OrderSettleStatus;
import com.sh.carexx.common.enums.order.OrderStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.CustomerOrder;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.OrderSettle;
import com.sh.carexx.uc.service.CustomerOrderScheduleService;
import com.sh.carexx.uc.service.CustomerOrderService;
import com.sh.carexx.uc.service.OrderSettleService;

/**
 * 
 * ClassName: CustomerOrderScheduleManager <br/>
 * Function: 订单排班 <br/>
 * Date: 2018年5月29日 下午4:41:18 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class CustomerOrderScheduleManager {
	@Autowired
	private CustomerOrderScheduleService customerOrderScheduleService;
	@Autowired
	private OrderSettleManager orderSettleManager;
	@Autowired
	private CustomerOrderService customerOrderService;
	@Autowired
	public OrderSettleService orderSettleService;

	/**
	 * 
	 * add:(添加排班，拆分日期). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderScheduleFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void add(CustomerOrderScheduleFormBean customerOrderScheduleFormBean) throws BizException {
		Date serviceStartTime = null;
		Date serviceEndTime = null;
		if (ValidUtils.isDateTime(customerOrderScheduleFormBean.getServiceStartTime())) {
			serviceStartTime = DateUtils.toDate(customerOrderScheduleFormBean.getServiceStartTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS);
		}
		if (ValidUtils.isDateTime(customerOrderScheduleFormBean.getServiceEndTime())) {
			serviceEndTime = DateUtils.toDate(customerOrderScheduleFormBean.getServiceEndTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS);
		}
		List<CustomerOrderSchedule> schedulelist = this.customerOrderScheduleService
				.queryByExistence(customerOrderScheduleFormBean.getOrderNo(), serviceStartTime, serviceEndTime);
		if (schedulelist.size() > 0) {
			throw new BizException(ErrorCode.ORDER_SCHEDULE_EXISTS_ERROR);
		}
		if (!serviceStartTime.before(serviceEndTime)) {
			throw new BizException(ErrorCode.TIME_END_BEFORE_START_ERROR);
		}

		int hourNum = DateUtils.getHourDiff(serviceStartTime, serviceEndTime);
		int amountNum = 0;
		Date amountStartDate = serviceStartTime;
		Date amountEndDate = serviceStartTime;
		if (DateUtils.getHourOfDay(serviceStartTime) == 8) {
			if (hourNum < 24) {
				this.doShedule(customerOrderScheduleFormBean, serviceStartTime, serviceEndTime);
			} else {
				while (amountNum < hourNum) {
					if (hourNum - amountNum < 24) {
						this.doShedule(customerOrderScheduleFormBean, amountEndDate, serviceEndTime);
						break;
					}
					amountStartDate = amountEndDate;
					amountEndDate = DateUtils.addDay(amountStartDate, 1);
					amountNum += 24;
					this.doShedule(customerOrderScheduleFormBean, amountStartDate, amountEndDate);
				}
			}
		} else {
			if (hourNum < 24) {
				this.doShedule(customerOrderScheduleFormBean, serviceStartTime, serviceEndTime);
			} else {
				amountStartDate = amountEndDate;
				amountEndDate = DateUtils.addHour(amountStartDate, 12);
				amountNum += 12;
				this.doShedule(customerOrderScheduleFormBean, amountStartDate, amountEndDate);
				while (amountNum < hourNum) {
					if (hourNum - amountNum < 24) {
						this.doShedule(customerOrderScheduleFormBean, amountEndDate, serviceEndTime);
						break;
					}
					amountStartDate = amountEndDate;
					amountEndDate = DateUtils.addDay(amountStartDate, 1);
					amountNum += 24;
					this.doShedule(customerOrderScheduleFormBean, amountStartDate, amountEndDate);
				}
			}
		}
	}

	/**
	 * 
	 * doShedule:(添加排班). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderScheduleFormBean
	 * @param amountStartDate
	 * @param amountEndDate
	 * @throws BizException
	 * @since JDK 1.8
	 */
	private void doShedule(CustomerOrderScheduleFormBean customerOrderScheduleFormBean, Date amountStartDate,
			Date amountEndDate) throws BizException {
		CustomerOrderSchedule customerOrderSchedule = new CustomerOrderSchedule();
		customerOrderSchedule.setOrderNo(customerOrderScheduleFormBean.getOrderNo());
		customerOrderSchedule.setServiceStaffId(customerOrderScheduleFormBean.getServiceStaffId());
		customerOrderSchedule.setServiceStartTime(amountStartDate);
		customerOrderSchedule.setServiceEndTime(amountEndDate);
		BigDecimal orderAmt = new BigDecimal(customerOrderScheduleFormBean.getOrderAmt());
		if(orderAmt.compareTo(BigDecimal.ZERO)==-1){
			customerOrderSchedule.setServiceDuration(DateUtils.getHourDiff(amountStartDate, amountEndDate)*(-1));
		}else{
			customerOrderSchedule.setServiceDuration(DateUtils.getHourDiff(amountStartDate, amountEndDate));
		}
		customerOrderSchedule.setWorkTypeSettleId(customerOrderScheduleFormBean.getWorkTypeSettleId());
		customerOrderSchedule.setServiceStatus(OrderScheduleStatus.IN_SERVICE.getValue());
		this.customerOrderScheduleService.save(customerOrderSchedule);
		this.orderSettleManager.add(customerOrderSchedule);

		CustomerOrder customerOrder = this.customerOrderService
				.getByOrderNo(customerOrderScheduleFormBean.getOrderNo());
		List<CustomerOrderSchedule> orderScheduleList = this.customerOrderScheduleService
				.getByOrderNo(customerOrderScheduleFormBean.getOrderNo());
		int hourNum = DateUtils.getHourDiff(customerOrder.getServiceStartTime(), customerOrder.getServiceEndTime());
		int hour = 0;
		for (CustomerOrderSchedule schedule : orderScheduleList) {
			hour += DateUtils.getHourDiff(schedule.getServiceStartTime(), schedule.getServiceEndTime());
		}
		if (hour == hourNum) {
			this.customerOrderService.updateStatus(customerOrderScheduleFormBean.getOrderNo(),
					OrderStatus.WAIT_SCHEDULE.getValue(), OrderStatus.IN_SERVICE.getValue());
		}
	}

	/**
	 * 
	 * delete:(删除排班). <br/>
	 * 
	 * @author hetao
	 * @param id：排班id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void delete(Long id) throws BizException {
		this.customerOrderScheduleService.updateStatus(id, OrderScheduleStatus.IN_SERVICE.getValue(),
				OrderScheduleStatus.DELETED.getValue());
		this.orderSettleService.updateStatus(id, OrderSettleStatus.SETTLING.getValue(),
				OrderSettleStatus.CANCELED.getValue());
	}

	/**
	 * 
	 * confirmCompleted:(确认完成排班). <br/>
	 * 
	 * @author hetao
	 * @param id：排班id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void confirmCompleted(Long id) throws BizException {
		CustomerOrderSchedule customerOrderSchedule = this.customerOrderScheduleService.getById(id);
		if (customerOrderSchedule.getServiceStatus() == OrderScheduleStatus.COMPLETED.getValue()) {
			return;
		}
		this.customerOrderScheduleService.updateStatus(id, OrderScheduleStatus.IN_SERVICE.getValue(),
				OrderScheduleStatus.COMPLETED.getValue());
		this.orderSettleService.updateStatus(id, OrderSettleStatus.SETTLING.getValue(),
				OrderSettleStatus.SETTLED.getValue());
		List<CustomerOrderSchedule> orderScheduleList = this.customerOrderScheduleService
				.getByOrderNo(customerOrderSchedule.getOrderNo());
		boolean flag = true;
		for (CustomerOrderSchedule schedule : orderScheduleList) {
			if (schedule.getServiceStatus() != OrderScheduleStatus.COMPLETED.getValue()) {
				flag = false;
				break;
			}
		}
		if (flag) {
			this.customerOrderService.updateStatus(customerOrderSchedule.getOrderNo(),
					OrderStatus.IN_SERVICE.getValue(), OrderStatus.WAIT_PAY.getValue());
		}
	}

	/**
	 * 
	 * batchDeleteSchedule:(批量删除排班). <br/>
	 * 
	 * @author hetao
	 * @param ids：排班id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void batchDelete(String ids) throws BizException {
		String[] strArr = ids.split(",");
		long[] longArr = new long[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			longArr[i] = Long.valueOf(strArr[i]);
		}
		for (int i = 0; i < longArr.length; i++) {
			this.customerOrderScheduleService.updateStatus(longArr[i], OrderScheduleStatus.IN_SERVICE.getValue(),
					OrderScheduleStatus.DELETED.getValue());
			this.orderSettleService.updateStatus(longArr[i], OrderSettleStatus.SETTLING.getValue(),
					OrderSettleStatus.CANCELED.getValue());
		}
	}

	/**
	 * 
	 * batchconfirmcompleted:(批量完成排班). <br/>
	 * 
	 * @author hetao
	 * @param ids：排班id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void batchConfirmComplete(String ids) throws BizException {
		CustomerOrderSchedule customerOrderSchedule = new CustomerOrderSchedule();
		String[] strArr = ids.split(",");
		long[] longArr = new long[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			longArr[i] = Long.valueOf(strArr[i]);
		}
		for (int i = 0; i < longArr.length; i++) {
			customerOrderSchedule = this.customerOrderScheduleService.getById(longArr[i]);
			this.customerOrderScheduleService.updateStatus(longArr[i], OrderScheduleStatus.IN_SERVICE.getValue(),
					OrderScheduleStatus.COMPLETED.getValue());
		}

		List<CustomerOrderSchedule> orderScheduleList = this.customerOrderScheduleService
				.getByOrderNo(customerOrderSchedule.getOrderNo());
		boolean flag = true;
		for (CustomerOrderSchedule schedule : orderScheduleList) {
			if (schedule.getServiceStatus() != OrderScheduleStatus.COMPLETED.getValue()) {
				flag = false;
				break;
			}
		}
		if (flag) {
			this.customerOrderService.updateStatus(customerOrderSchedule.getOrderNo(),
					OrderStatus.IN_SERVICE.getValue(), OrderStatus.WAIT_PAY.getValue());
		}
	}

	/**
	 * 
	 * modifySettleAmt:(调整结算金额). <br/> 
	 * 
	 * @author zhoulei 
	 * @param orderSettleAdjustAmtFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void modifySettleAmt(OrderSettleAdjustAmtFormBean orderSettleAdjustAmtFormBean) throws BizException {
		OrderSettle orderSettle = this.orderSettleService.getByScheduleId(orderSettleAdjustAmtFormBean.getScheduleId());
		if (orderSettle == null) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		//校验调整的金额是否超出
		if (orderSettle.getStaffSettleAmt().add(new BigDecimal(orderSettleAdjustAmtFormBean.getAdjustAmt()))
				.compareTo(new BigDecimal("0.00")) == -1
				|| orderSettle.getInstSettleAmt().subtract(new BigDecimal(orderSettleAdjustAmtFormBean.getAdjustAmt()))
						.compareTo(new BigDecimal("0.00")) == -1) {
			throw new BizException(ErrorCode.SETTLE_AMT_BEYOND_ERROR);
		}
		orderSettle.setStaffSettleAmt(
				orderSettle.getStaffSettleAmt().add(new BigDecimal(orderSettleAdjustAmtFormBean.getAdjustAmt())));
		orderSettle.setInstSettleAmt(
				orderSettle.getInstSettleAmt().subtract(new BigDecimal(orderSettleAdjustAmtFormBean.getAdjustAmt())));
		orderSettle.setAdjustAmt(
				orderSettle.getAdjustAmt().add(new BigDecimal(orderSettleAdjustAmtFormBean.getAdjustAmt())));

		this.orderSettleService.updateSettleAmt(orderSettle);
	}
}
