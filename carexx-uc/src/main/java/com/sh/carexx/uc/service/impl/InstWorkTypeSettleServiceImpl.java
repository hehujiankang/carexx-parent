package com.sh.carexx.uc.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.worktype.WorkTypeSettleQueryFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstWorkTypeSettle;
import com.sh.carexx.uc.dao.InstWorkTypeSettleMapper;
import com.sh.carexx.uc.service.InstWorkTypeSettleService;

@Service
public class InstWorkTypeSettleServiceImpl implements InstWorkTypeSettleService {

	@Autowired
	private InstWorkTypeSettleMapper instWorkTypeSettleMapper;

	@Override
	public InstWorkTypeSettle getById(Integer id) {
		return this.instWorkTypeSettleMapper.selectById(id);
	}

	@Override
	public List<InstWorkTypeSettle> queryAllAvailable(Integer instId, Integer workTypeId) {
		return this.instWorkTypeSettleMapper.selectAllAvailable(instId, workTypeId);
	}

	@Override
	public Integer getInstWorkTypeSettleCount(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean) {
		return this.instWorkTypeSettleMapper.selectInstWorkTypeSettleCount(workTypeSettleQueryFormBean);
	}

	@Override
	public List<Map<?, ?>> queryInstWorkTypeSettleList(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean) {
		return this.instWorkTypeSettleMapper.selectInstWorkTypeSettleList(workTypeSettleQueryFormBean);
	}

	@Override
	public void save(InstWorkTypeSettle instWorkTypeSettle) throws BizException {
		int rows = 0;
		try {
			rows = this.instWorkTypeSettleMapper.insert(instWorkTypeSettle);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(InstWorkTypeSettle instWorkTypeSettle) throws BizException {
		int rows = 0;
		try {
			rows = this.instWorkTypeSettleMapper.update(instWorkTypeSettle);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.instWorkTypeSettleMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public InstWorkTypeSettle queryByExistence(Integer instId, Integer workTypeId, BigDecimal settleRatio) {
		return this.instWorkTypeSettleMapper.selectByExistence(instId, workTypeId, settleRatio);
	}

}
