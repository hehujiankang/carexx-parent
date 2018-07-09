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

	public void add(CustomerOrder customerOrder) throws BizException {
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void syncPayResult(OrderPayment orderPayment) throws BizException {
		OrderPayment oldOrderPayment = this.orderPaymentService.getByOrderNo(orderPayment.getOrderNo());
		if (oldOrderPayment.getPayStatus() == PayStatus.PENDING_PAY.getValue()
				&& orderPayment.getPayStatus() == PayStatus.PAY_SUCCESS.getValue()) {
			this.customerOrderService.updateStatus(orderPayment.getOrderNo(), OrderStatus.WAIT_PAY.getValue(),
					OrderStatus.ALREADY_PAY.getValue());
			List<CustomerOrderSchedule> orderScheduleList = this.customerOrderScheduleService
					.getByOrderNo(orderPayment.getOrderNo());
			for (CustomerOrderSchedule orderSchedule : orderScheduleList) {
				this.orderSettleService.updateStatus(orderSchedule.getId(), OrderSettleStatus.SETTLING.getValue(),
						OrderSettleStatus.SETTLED.getValue());
			}
		}
		this.orderPaymentService.update(orderPayment);
	}

	public void offlinePayment(String orderNo) throws BizException {
		OrderPayment orderPayment = new OrderPayment();
		orderPayment.setOrderNo(orderNo);
		orderPayment.setPayType(PayMethod.UNDERLINE_PAY.getValue());
		orderPayment.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
		this.orderPaymentService.update(orderPayment);
	}

}
