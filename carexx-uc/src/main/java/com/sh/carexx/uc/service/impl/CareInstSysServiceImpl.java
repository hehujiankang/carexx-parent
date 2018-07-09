package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.CareInstSysFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInstSys;
import com.sh.carexx.uc.dao.CareInstSysMapper;
import com.sh.carexx.uc.service.CareInstSysService;

@Service
public class CareInstSysServiceImpl implements CareInstSysService {

	@Autowired
	private CareInstSysMapper careInstSysMapper;

	@Override
	public CareInstSys getByInstSysName(CareInstSysFormBean careInstSysFormBean) {
		return this.careInstSysMapper.selectByInstSysName(careInstSysFormBean);
	}
	
	@Override
	public Integer getCareInstSysCount(CareInstSysFormBean careInstSysFormBean) {
		return this.careInstSysMapper.selectCareInstSysCount(careInstSysFormBean);
	}

	@Override
	public List<Map<?, ?>> queryCareInstSysList(CareInstSysFormBean careInstSysFormBean) {
		return this.careInstSysMapper.selectCareInstSysList(careInstSysFormBean);
	}

	@Override
	public List<CareInstSys> queryAllCareInstSys(Integer instId) {
		return this.careInstSysMapper.selectAllCareInstSys(instId);
	}

	@Override
	public void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.careInstSysMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	public void save(CareInstSys careInstSys) throws BizException {
		int rows = 0;
		try {
			rows = this.careInstSysMapper.insert(careInstSys);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
