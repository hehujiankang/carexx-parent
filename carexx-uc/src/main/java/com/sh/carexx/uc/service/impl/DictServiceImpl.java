package com.sh.carexx.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.dict.DictFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.Dict;
import com.sh.carexx.uc.dao.DictMapper;
import com.sh.carexx.uc.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;

	@Override
	public void save(Dict dict) throws BizException {
		int rows = 0;
		try {
			rows = this.dictMapper.insert(dict);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Dict getById(Integer id) {
		return this.dictMapper.selectById(id);
	}

	@Override
	public void update(Dict dict) throws BizException {
		int rows = 0;
		try {
			rows = this.dictMapper.update(dict);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getDictCount(DictFormBean dictFormBean) {
		return this.dictMapper.selectDictCount(dictFormBean);
	}

	@Override
	public List<Dict> queryDictList(DictFormBean dictFormBean) {
		return this.dictMapper.selectDictList(dictFormBean);
	}

	@Override
	public void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.dictMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
