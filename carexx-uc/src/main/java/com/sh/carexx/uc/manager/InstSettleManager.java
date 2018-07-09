package com.sh.carexx.uc.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.enums.order.OrderSettleStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.InstSettle;
import com.sh.carexx.uc.service.CustomerOrderScheduleService;
import com.sh.carexx.uc.service.InstSettleService;
import com.sh.carexx.uc.service.OrderSettleService;

@Service
public class InstSettleManager {
	@Autowired
	private InstSettleService instSettleService;
	@Autowired
	private CustomerOrderScheduleService customerOrderScheduleService;
	@Autowired
	private OrderSettleService orderSettleService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void add(InstSettle instSettle) throws BizException {
		Date recentlySettleDate = this.instSettleService.getBySettleDate(null, instSettle.getInstId());
		if (recentlySettleDate != null && instSettle.getSettleDate().compareTo(recentlySettleDate) != 1) {
			throw new BizException(ErrorCode.INST_SETTLE_SETTLE_DATE_GREATER_ERROR);
		}

		List<CustomerOrderSchedule> scheduleList = this.customerOrderScheduleService
				.getByTime(recentlySettleDate, instSettle.getSettleDate(), OrderSettleStatus.SETTLED.getValue(), instSettle.getInstId());
		if(scheduleList.size() > 0 ) {
			this.orderSettleService.batchCloseUpdate(scheduleList);
		}
		this.instSettleService.save(instSettle);
		
		List<InstSettle> instSettleList = this.instSettleService.queryAllBySettleStatus(instSettle.getSettleDate(), instSettle.getInstId());
		if(instSettleList.size() > 0) {
			for(InstSettle oldinstSettle : instSettleList) {
				Date oldRecentlySettleDate = this.instSettleService.getBySettleDate(oldinstSettle.getSettleDate(), oldinstSettle.getInstId());
				List<CustomerOrderSchedule> oldScheduleList = this.customerOrderScheduleService
						.getByTime(oldRecentlySettleDate, oldinstSettle.getSettleDate(), OrderSettleStatus.SETTLED.getValue(), oldinstSettle.getInstId());
				if(oldScheduleList.size() > 0 ) {
					this.orderSettleService.batchCloseUpdate(oldScheduleList);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void closeSettle(Long id, String modifier) throws BizException {
		InstSettle instSettle = this.instSettleService.getById(id);
		Date recentlySettleDate = this.instSettleService.getBySettleDate(instSettle.getSettleDate(), instSettle.getInstId());
		List<CustomerOrderSchedule> scheduleList = this.customerOrderScheduleService
				.getByTime(recentlySettleDate, instSettle.getSettleDate(), OrderSettleStatus.SETTLED.getValue(), instSettle.getInstId());
		if(scheduleList.size() > 0 ) {
			this.orderSettleService.batchCloseUpdate(scheduleList);
		}
		this.instSettleService.updateStatus(id, modifier, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
		
		List<InstSettle> instSettleList = this.instSettleService.queryAllBySettleStatus(instSettle.getSettleDate(), instSettle.getInstId());
		if(instSettleList.size() > 0) {
			for(InstSettle oldinstSettle : instSettleList) {
				Date oldRecentlySettleDate = this.instSettleService.getBySettleDate(oldinstSettle.getSettleDate(), oldinstSettle.getInstId());
				List<CustomerOrderSchedule> oldScheduleList = this.customerOrderScheduleService
						.getByTime(oldRecentlySettleDate, oldinstSettle.getSettleDate(), OrderSettleStatus.SETTLED.getValue(), oldinstSettle.getInstId());
				if(oldScheduleList.size() > 0 ) {
					this.orderSettleService.batchCloseUpdate(oldScheduleList);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void openSettle(Long id, String modifier) throws BizException {
		InstSettle instSettle = this.instSettleService.getById(id);
		Date recentlySettleDate = this.instSettleService.getBySettleDate(instSettle.getSettleDate(), instSettle.getInstId());
		List<CustomerOrderSchedule> scheduleList = this.customerOrderScheduleService
				.getByTime(recentlySettleDate, instSettle.getSettleDate(), OrderSettleStatus.CLOSED.getValue(), instSettle.getInstId());
		if(scheduleList.size() > 0 ) {
			this.orderSettleService.batchOpenUpdate(scheduleList);
		}
		this.instSettleService.updateStatus(id, modifier, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
