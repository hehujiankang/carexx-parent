package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/inststaffworktype")
public class InstStaffWorkTypeController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstStaffWorkTypeFormBean instStaffWorkTypeFormBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstStaffWorkType(instStaffWorkTypeFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return this.ucServiceClient.queryInstStaffWorkTypeForList(instStaffWorkTypeFormBean);
	}

	@RequestMapping(value = "/all_by_staffid/{staffId}", method = RequestMethod.GET)
	public String queryAllByStaffId(@PathVariable("staffId") Integer staffId) {
		return this.ucServiceClient.queryInstStaffWorkTypeByStaffId(staffId);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstStaffWorkTypeFormBean instStaffWorkTypeFormBean, BindingResult bindingResult) {
		if (instStaffWorkTypeFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstStaffWorkType(instStaffWorkTypeFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Long id) {
		return this.ucServiceClient.enableInstStaffWorkType(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Long id) {
		return this.ucServiceClient.disableInstStaffWorkType(id);
	}
}
