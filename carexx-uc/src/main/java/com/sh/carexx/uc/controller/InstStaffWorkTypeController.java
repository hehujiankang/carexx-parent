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

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.uc.manager.InstStaffWorkTypeManager;
import com.sh.carexx.uc.service.InstStaffWorkTypeService;

@RestController
@RequestMapping("/inststaffworktype")
public class InstStaffWorkTypeController {

	@Autowired
	public InstStaffWorkTypeService instStaffWorkTypeService;

	@Autowired
	public InstStaffWorkTypeManager instStaffWorkTypeManager;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		try {
			this.instStaffWorkTypeManager.add(instStaffWorkTypeFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		Integer totalNum = this.instStaffWorkTypeService.getInstStaffWorkTypeCount(instStaffWorkTypeFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.instStaffWorkTypeService.queryInstStaffWorkTypeList(instStaffWorkTypeFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/all_by_staffid/{staffId}", method = RequestMethod.GET)
	public String queryAllByStaffId(@PathVariable("staffId") Integer staffId) {
		List<Map<?, ?>> resultList = null;
		resultList = this.instStaffWorkTypeService.getByStaffId(staffId);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, resultList).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		try {
			this.instStaffWorkTypeManager.modify(instStaffWorkTypeFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	public BasicRetVal enable(@PathVariable("id") Long id) {
		try {
			this.instStaffWorkTypeManager.enable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	public BasicRetVal disable(@PathVariable("id") Long id) {
		try {
			this.instStaffWorkTypeManager.disable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
