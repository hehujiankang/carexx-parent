package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInst;
import com.sh.carexx.uc.dao.CareInstMapper;
import com.sh.carexx.uc.service.CareInstService;

@Service
public class CareInstServiceImpl implements CareInstService {

	@Autowired
	private CareInstMapper careInstMapper;

	public void save(CareInst careInst) throws BizException {
		int rows = 0;
		try {
			rows = this.careInstMapper.insert(careInst);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}
	
	@Override
	public CareInst getById(Integer id) {
		return this.careInstMapper.selectById(id);
	}

	@Override
	public CareInst getByInstName(CareInstFormBean careInstFormBean) {
		return this.careInstMapper.selectByInstName(careInstFormBean);
	}

	@Override
	public Integer getCareInstCount(CareInstFormBean careInstFormBean) {
		return this.careInstMapper.selectCareInstCount(careInstFormBean);
	}

	@Override
	public List<Map<?, ?>> queryCareInstList(CareInstFormBean careInstFormBean) {
		return this.careInstMapper.selectCareInstList(careInstFormBean);
	}

	@Override
	public List<Map<?, ?>> queryAllCareInst(CareInstFormBean careInstFormBean) {
		return this.careInstMapper.selectAllByInstName(careInstFormBean);
	}

	@Override
	public void update(CareInst careInst) throws BizException {
		int rows = 0;
		try {
			rows = this.careInstMapper.update(careInst);
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
			rows = this.careInstMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
