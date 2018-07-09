package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstCareService;
import com.sh.carexx.uc.dao.InstCareServiceMapper;
import com.sh.carexx.uc.service.InstCareServiceService;

@Service
public class InstCareServiceImpl implements InstCareServiceService {

	@Autowired
	private InstCareServiceMapper instCareServiceMapper;

	@Override
	public void save(InstCareService instCareService) throws BizException {
		int rows = 0;
		try {
			rows = this.instCareServiceMapper.insert(instCareService);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(InstCareService instCareService) throws BizException {
		int rows = 0;
		try {
			rows = this.instCareServiceMapper.update(instCareService);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getInstCareServiceCount(InstServiceQueryFormBean instServiceQueryFormBean) {
		return this.instCareServiceMapper.selectInstCareServiceCount(instServiceQueryFormBean);
	}

	@Override
	public List<Map<?, ?>> queryInstCareServiceList(InstServiceQueryFormBean instServiceQueryFormBean) {
		return this.instCareServiceMapper.selectInstCareServiceList(instServiceQueryFormBean);
	}

	@Override
	public List<Map<?, ?>> queryAllAvailable(InstServiceQueryFormBean instServiceQueryFormBean) {
		return this.instCareServiceMapper.selectAllAvailable(instServiceQueryFormBean);
	}

	@Override
	public void delete(Integer id) throws BizException {
		int rows = 0;
		try {
			rows = this.instCareServiceMapper.delete(id);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public InstCareService queryServicePrice(Integer instId, Integer serviceId) {
		return this.instCareServiceMapper.selectServicePrice(instId, serviceId);
	}

	@Override
	public InstCareService queryExistence(InstCareServiceFormBean instCareServiceFormBean) {
		return this.instCareServiceMapper.selectExistence(instCareServiceFormBean);
	}
}
