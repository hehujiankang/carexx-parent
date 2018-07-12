package com.sh.carexx.uc.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.order.OrderSettleStatus;
import com.sh.carexx.common.enums.order.OrderStatus;
import com.sh.carexx.common.enums.pay.PayMethod;
import com.sh.carexx.common.enums.pay.PayStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrder;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.OrderPayment;
import com.sh.carexx.uc.service.CustomerOrderScheduleService;
import com.sh.carexx.uc.service.CustomerOrderService;
import com.sh.carexx.uc.service.OrderPaymentService;
import com.sh.carexx.uc.service.OrderSettleService;

/**
 * 
 * ClassName: 订单支付 <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午1:51:36 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class OrderPaymentManager {

	@Autowired
	private OrderPaymentService orderPaymentService;

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private CustomerOrderScheduleService customerOrderScheduleService;

	@Autowired
	public OrderSettleService orderSettleService;

	/**
	 * 
	 * add:(支付信息添加). <br/> 
	 * 
	 * @author zhoulei 
	 * @param customerOrder
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(CustomerOrder customerOrder) throws BizException {
		//支付信息校验订单号
		OrderPayment oldOrderPayment = this.orderPaymentService.getByOrderNo(customerOrder.getOrderNo());
		if (oldOrderPayment != null) {
			throw new BizException(ErrorCode.ORDER_PAYMENT_EXISTS_ERROR);
		}
		OrderPayment orderPayment = new OrderPayment();
		orderPayment.setOrderNo(customerOrder.getOrderNo());
		orderPayment.setPayAmt(customerOrder.getOrderAmt());
		orderPayment.setPayStatus(PayStatus.PENDING_PAY.getValue());

		this.orderPaymentService.save(orderPayment);

	}

	/**
	 * 
	 * syncPayResult:(同步支付结果). <br/> 
	 * 
	 * @author zhoulei 
	 * @param orderPayment
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void syncPayResult(OrderPayment orderPayment) throws BizException {
		OrderPayment oldOrderPayment = this.orderPaymentService.getByOrderNo(orderPayment.getOrderNo());
		if (oldOrderPayment.getPayStatus() == PayStatus.PENDING_PAY.getValue()
				&& orderPayment.getPayStatus() == PayStatus.PAY_SUCCESS.getValue()) {
			//订单表待支付变成已支付
			this.customerOrderService.updateStatus(orderPayment.getOrderNo(), OrderStatus.WAIT_PAY.getValue(),
					OrderStatus.ALREADY_PAY.getValue());
			//结算表待支付变成已支付
			List<CustomerOrderSchedule> orderScheduleList = this.customerOrderScheduleService
					.getByOrderNo(orderPayment.getOrderNo());
			for (CustomerOrderSchedule orderSchedule : orderScheduleList) {
				this.orderSettleService.updateStatus(orderSchedule.getId(), OrderSettleStatus.SETTLING.getValue(),
						OrderSettleStatus.SETTLED.getValue());
			}
		}
		this.orderPaymentService.update(orderPayment);
	}

	/**
	 * 
	 * offlinePayment:(线下支付). <br/> 
	 * 
	 * @author zhoulei 
	 * @param orderNo
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void offlinePayment(String orderNo) throws BizException {
		OrderPayment orderPayment = new OrderPayment();
		orderPayment.setOrderNo(orderNo);
		orderPayment.setPayType(PayMethod.UNDERLINE_PAY.getValue());
		orderPayment.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
		this.orderPaymentService.update(orderPayment);
	}

}
