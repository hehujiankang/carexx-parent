package com.sh.carexx.uc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstHoliday;
import com.sh.carexx.uc.dao.InstHolidayMapper;
import com.sh.carexx.uc.service.InstHolidayService;

@Service
public class InstHolidayServiceImpl implements InstHolidayService {

	@Autowired
	private InstHolidayMapper instHolidayMapper;

	@Override
	public void save(InstHoliday instHoliday) throws BizException {
		int rows = 0;
		try {
			rows = this.instHolidayMapper.insert(instHoliday);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public InstHoliday getByScheduleDate(Integer instId, Date scheduleDate) {
		return this.instHolidayMapper.selectByScheduleDate(instId, scheduleDate);
	}

	@Override
	public List<InstHoliday> getOrderByExistence(InstHolidayFormBean instHolidayFormBean) {
		return this.instHolidayMapper.selectByExistence(instHolidayFormBean);
	}

	@Override
	public Integer getInstHolidayCount(InstHolidayFormBean instHolidayFormBean) {
		return this.instHolidayMapper.selectInstHolidayCount(instHolidayFormBean);
	}

	@Override
	public List<Map<?, ?>> queryInstHolidayList(InstHolidayFormBean instHolidayFormBean) {
		return this.instHolidayMapper.selectInstHolidayList(instHolidayFormBean);
	}

	@Override
	public void update(InstHoliday instHoliday) throws BizException {
		int rows = 0;
		try {
			rows = this.instHolidayMapper.update(instHoliday);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void delete(Long id) throws BizException {
		int rows = 0;
		try {
			rows = this.instHolidayMapper.delete(id);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
