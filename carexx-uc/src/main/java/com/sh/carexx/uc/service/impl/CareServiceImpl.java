package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareService;
import com.sh.carexx.uc.dao.CareServiceMapper;
import com.sh.carexx.uc.service.CareServiceService;

@Service
public class CareServiceImpl implements CareServiceService {

	@Autowired
	private CareServiceMapper careServiceMapper;

	@Override
	public void save(CareService careService) throws BizException {
		int rows = 0;
		try {
			rows = this.careServiceMapper.insert(careService);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public CareService getById(Integer id) {
		return this.careServiceMapper.selectById(id);
	}

	@Override
	public List<Map<?, ?>> queryCareServiceList(InstServiceQueryFormBean instServiceQueryFormBean) {
		return this.careServiceMapper.selectCareServiceList(instServiceQueryFormBean);
	}

	@Override
	public List<CareService> queryAllAvailable() {
		return this.careServiceMapper.selectAllAvailable();
	}

	@Override
	public Integer getCareServiceCount(InstServiceQueryFormBean instServiceQueryFormBean) {
		return this.careServiceMapper.selectCareServiceCount(instServiceQueryFormBean);
	}

	@Override
	public void update(CareService careService) throws BizException {
		int rows = 0;
		try {
			rows = this.careServiceMapper.update(careService);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateServiceStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.careServiceMapper.updateServiceStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public CareService getByworkTypeIdAndserviceName(Integer workTypeId, String serviceName) {
		return this.careServiceMapper.selectByworkTypeIdAndserviceName(workTypeId, serviceName);
	}

}
