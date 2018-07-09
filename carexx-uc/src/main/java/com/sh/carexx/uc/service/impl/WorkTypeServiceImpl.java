package com.sh.carexx.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.worktype.WorkTypeFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.WorkType;
import com.sh.carexx.uc.dao.WorkTypeMapper;
import com.sh.carexx.uc.service.WorkTypeService;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {
	@Autowired
	private WorkTypeMapper workTypeMapper;

	@Override
	public void save(WorkType workType) throws BizException {
		int rows = 0;
		try {
			rows = this.workTypeMapper.insert(workType);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(WorkType workType) throws BizException {
		int rows = 0;
		try {
			rows = this.workTypeMapper.update(workType);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public List<WorkType> queryAllAvailable() {
		return this.workTypeMapper.selectAllAvailable();
	}

	@Override
	public void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.workTypeMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getWorkTypeCount() {
		return this.workTypeMapper.selectWorkTypeCount();
	}

	@Override
	public List<WorkType> queryWorkTypeList(WorkTypeFormBean workTypeFormBean) {
		return this.workTypeMapper.selectWorkTypeList(workTypeFormBean);
	}

	@Override
	public WorkType getByWorkTypeName(String workTypeName) {
		return this.workTypeMapper.selectByWorkTypeName(workTypeName);
	}

}
