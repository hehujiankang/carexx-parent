package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.OrderSettle;
import com.sh.carexx.uc.dao.OrderSettleMapper;
import com.sh.carexx.uc.service.OrderSettleService;

@Service
public class OrderSettleServiceImpl implements OrderSettleService {

	@Autowired
	public OrderSettleMapper orderSettleMapper;

	@Override
	public void save(OrderSettle orderSettle) throws BizException {
		int rows = 0;
		try {
			rows = this.orderSettleMapper.insert(orderSettle);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}
	
	@Override
	public OrderSettle getByScheduleId(Long scheduleId) {
		return this.orderSettleMapper.selectByScheduleId(scheduleId);
	}

	@Override
	public void updateStatus(Long scheduleId, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.orderSettleMapper.updateStatus(scheduleId, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void batchCloseUpdate(List<CustomerOrderSchedule> scheduleList) throws BizException {
		int rows = 0;
		try {
			rows = this.orderSettleMapper.batchCloseUpdate(scheduleList);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != scheduleList.size()) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void batchOpenUpdate(List<CustomerOrderSchedule> scheduleList) throws BizException {
		int rows = 0;
		try {
			rows = this.orderSettleMapper.batchOpenUpdate(scheduleList);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != scheduleList.size()) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateSettleAmt(OrderSettle orderSettle) throws BizException {
		int rows = 0;
		try {
			rows = this.orderSettleMapper.updateSettleAmt(orderSettle);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public List<Map<?, ?>> queryOrderSettleCount(WorkQuantityReportFormBean workQuantityReportFormBean) {
		return this.orderSettleMapper.selectOrderSettleCount(workQuantityReportFormBean);
	}

}
