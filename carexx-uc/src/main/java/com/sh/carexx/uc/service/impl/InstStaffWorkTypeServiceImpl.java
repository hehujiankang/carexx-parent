package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstStaffWorkType;
import com.sh.carexx.uc.dao.InstStaffWorkTypeMapper;
import com.sh.carexx.uc.service.InstStaffWorkTypeService;

@Service
public class InstStaffWorkTypeServiceImpl implements InstStaffWorkTypeService {

	@Autowired
	private InstStaffWorkTypeMapper instStaffWorkTypeMapper;

	@Override
	public void save(InstStaffWorkType instStaffWorkType) throws BizException {
		int row = 0;
		try {
			row = instStaffWorkTypeMapper.insert(instStaffWorkType);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (row != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public InstStaffWorkType getById(Long id) {
		return this.instStaffWorkTypeMapper.selectById(id);
	}
	
	@Override
	public List<Map<?, ?>> getByStaffId(Integer staffId) {
		return this.instStaffWorkTypeMapper.selectByStaffId(staffId);
	}
	
	@Override
	public Integer getInstStaffWorkTypeCount(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return instStaffWorkTypeMapper.selectInstStaffWorkTypeCount(instStaffWorkTypeFormBean);
	}

	@Override
	public List<Map<?, ?>> queryInstStaffWorkTypeList(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return instStaffWorkTypeMapper.selectInstStaffWorkTypeList(instStaffWorkTypeFormBean);
	}
	
	@Override
	public InstStaffWorkType getByStaffIdAndWorkTypeId(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return instStaffWorkTypeMapper.selectByStaffIdAndWorkTypeId(instStaffWorkTypeFormBean);
	}
	
	@Override
	public void update(InstStaffWorkType instStaffWorkType) throws BizException {
		int rows = 0;
		try {
			rows = this.instStaffWorkTypeMapper.update(instStaffWorkType);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}

	}

	@Override
	public void updateStatus(Long id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.instStaffWorkTypeMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
