package com.sh.carexx.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.DictData;
import com.sh.carexx.uc.dao.DictDataMapper;
import com.sh.carexx.uc.service.DictDataService;

@Service
public class DictDataServiceImpl implements DictDataService {
	@Autowired
	private DictDataMapper dictDataMapper;

	@Override
	public List<DictData> getAllAvailableByDictId(Integer dictId) {
		return this.dictDataMapper.selectAllAvailableByDictId(dictId);
	}

	@Override
	public void update(DictData dictData) throws BizException {
		int rows = 0;
		try {
			rows = this.dictDataMapper.update(dictData);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void save(DictData dictData) throws BizException {
		int rows = 0;
		try {
			rows = this.dictDataMapper.insert(dictData);
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
			rows = this.dictDataMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getDictDataCount(DictDataFormBean dictDataFormBean) {
		return this.dictDataMapper.selectDictDataCount(dictDataFormBean);
	}

	@Override
	public List<DictData> queryDictDataList(DictDataFormBean dictDataFormBean) {
		return this.dictDataMapper.selectDictDataList(dictDataFormBean);
	}

}
