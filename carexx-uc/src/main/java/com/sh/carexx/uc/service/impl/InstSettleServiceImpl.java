package com.sh.carexx.uc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.order.InstSettleQueryFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstSettle;
import com.sh.carexx.uc.dao.InstSettleMapper;
import com.sh.carexx.uc.service.InstSettleService;

@Service
public class InstSettleServiceImpl implements InstSettleService {

	@Autowired
	private InstSettleMapper instSettleMapper;

	@Override
	public InstSettle getById(Long id) {
		return this.instSettleMapper.selectById(id);
	}

	@Override
	public Date getBySettleDate(Date settleDate, Integer instId) {
		return this.instSettleMapper.selectBySettleDate(settleDate, instId);
	}

	@Override
	public Date queryMaxSettleDateBySettleStatus(Integer instId) {
		return this.instSettleMapper.selectMaxSettleDateBySettleStatus(instId);
	}

	@Override
	public List<InstSettle> queryAllBySettleStatus(Date settleDate, Integer instId) {
		return this.instSettleMapper.selectAllBySettleStatus(settleDate, instId);
	}

	@Override
	public Integer getInstSettleCount(InstSettleQueryFormBean instSettleQueryFormBean) {
		return this.instSettleMapper.selectInstSettleCount(instSettleQueryFormBean);
	}

	@Override
	public List<Map<?, ?>> queryInstSettleList(InstSettleQueryFormBean instSettleQueryFormBean) {
		return this.instSettleMapper.selectInstSettleList(instSettleQueryFormBean);
	}

	@Override
	public void save(InstSettle instSettle) throws BizException {
		int rows = 0;
		try {
			rows = this.instSettleMapper.insert(instSettle);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateStatus(Long id, String modifier, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.instSettleMapper.updateStatus(id, modifier, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}
}
