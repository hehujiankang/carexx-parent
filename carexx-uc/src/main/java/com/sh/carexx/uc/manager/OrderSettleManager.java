package com.sh.carexx.uc.manager;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.common.enums.order.OrderSettleStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrder;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.InstStaff;
import com.sh.carexx.model.uc.InstWorkTypeSettle;
import com.sh.carexx.model.uc.OrderSettle;
import com.sh.carexx.uc.service.CustomerOrderService;
import com.sh.carexx.uc.service.InstStaffService;
import com.sh.carexx.uc.service.InstWorkTypeSettleService;
import com.sh.carexx.uc.service.OrderSettleService;

@Service
public class OrderSettleManager {

	@Autowired
	public OrderSettleService orderSettleService;

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private CustomerOrderManager customerOrderManager;

	@Autowired
	private InstWorkTypeSettleService instWorkTypeSettleService;

	@Autowired
	private InstStaffService instStaffService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void add(CustomerOrderSchedule customerOrderSchedule) throws BizException {
		// 比例
		InstWorkTypeSettle instWorkTypeSettle = instWorkTypeSettleService
				.getById(customerOrderSchedule.getWorkTypeSettleId());

		// 计算金额
		CustomerOrder customerOrder = customerOrderService.getByOrderNo(customerOrderSchedule.getOrderNo());
		BigDecimal serviceAmt = customerOrderManager.calcServiceFee(customerOrder.getInstId(),
				customerOrder.getServiceId(), customerOrderSchedule.getServiceStartTime(),
				customerOrderSchedule.getServiceEndTime());
		BigDecimal staffSettleAmt = serviceAmt.multiply(instWorkTypeSettle.getSettleRatio());
		BigDecimal instSettleAmt = serviceAmt.subtract(staffSettleAmt);

		// 人员id
		InstStaff instStaff = instStaffService.getById(customerOrderSchedule.getServiceStaffId());

		OrderSettle orderSettle = new OrderSettle();
		orderSettle.setScheduleId(customerOrderSchedule.getId());
		orderSettle.setSettleRatio(instWorkTypeSettle.getSettleRatio());
		orderSettle.setStaffId(instStaff.getId());
		orderSettle.setStaffSettleAmt(staffSettleAmt);
		orderSettle.setAdjustAmt(new  BigDecimal("0.00"));
		orderSettle.setSettleInstId(customerOrder.getInstId());
		orderSettle.setInstSettleAmt(instSettleAmt);
		orderSettle.setSettleStatus(OrderSettleStatus.SETTLING.getValue());
		orderSettleService.save(orderSettle);
	}
}
