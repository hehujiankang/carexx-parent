package com.sh.carexx.uc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.uc.manager.InstHolidayManager;
import com.sh.carexx.uc.service.InstHolidayService;

@RestController
@RequestMapping("/holiday")
public class InstHolidayController {

	@Autowired
	private InstHolidayService instHolidayService;

	@Autowired
	private InstHolidayManager instHolidayManager;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody InstHolidayFormBean instHolidayFormBean) {
		try {
			this.instHolidayManager.add(instHolidayFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody InstHolidayFormBean instHolidayFormBean) {
		Integer totalNum = this.instHolidayService.getInstHolidayCount(instHolidayFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.instHolidayService.queryInstHolidayList(instHolidayFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody InstHolidayFormBean instHolidayFormBean) {
		try {
			this.instHolidayManager.modify(instHolidayFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public BasicRetVal delete(@PathVariable("id") Long id) {
		try {
			this.instHolidayService.delete(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
