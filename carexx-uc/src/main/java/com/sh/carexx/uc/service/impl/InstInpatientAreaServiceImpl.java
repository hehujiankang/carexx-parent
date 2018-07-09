package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstInpatientArea;
import com.sh.carexx.uc.dao.InstInpatientAreaMapper;
import com.sh.carexx.uc.service.InstInpatientAreaService;

@Service
public class InstInpatientAreaServiceImpl implements InstInpatientAreaService{

	@Autowired
	private InstInpatientAreaMapper instInpatientAreaMapper;
	
	@Override
	public void save(InstInpatientArea instInpatientArea) throws BizException {
		int rows = 0;
		try {
			rows = this.instInpatientAreaMapper.insert(instInpatientArea);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getInstInpatientAreaCount(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		return this.instInpatientAreaMapper.selectInstInpatientAreaCount(instInpatientAreaFormBean);
	}

	@Override
	public List<Map<?, ?>> queryInstInpatientAreaList(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		return this.instInpatientAreaMapper.selectInstInpatientAreaList(instInpatientAreaFormBean);
	}

	@Override
	public void update(InstInpatientArea instInpatientArea) throws BizException {
		int rows = 0;
		try {
			rows = this.instInpatientAreaMapper.update(instInpatientArea);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void delete(Integer id) throws BizException {
		int rows = 0;
		try {
			rows = this.instInpatientAreaMapper.delete(id);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public InstInpatientArea getByInpatientArea(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		return this.instInpatientAreaMapper.selectByInpatientArea(instInpatientAreaFormBean);
	}

	@Override
	public List<InstInpatientArea> queryAllInstInpatientArea(Integer instId) {
		return this.instInpatientAreaMapper.selectAllInstInpatientArea(instId);
	}

}
