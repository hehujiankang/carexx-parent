package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.staff.InstStaffFormBean;
import com.sh.carexx.bean.staff.InstStaffQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/inststaff")
public class InstStaffController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstStaffFormBean instStaffFormBean, BindingResult bindingResult) {
		instStaffFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (instStaffFormBean.getInstId() == 0 || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstStaff(instStaffFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstStaffQueryFormBean instStaffQueryFormBean) {
		instStaffQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstStaffForList(instStaffQueryFormBean);
	}
	
	@RequestMapping(value = "/list_by_serviceid")
	public String queryForListByServiceId(InstStaffQueryFormBean instStaffQueryFormBean) {
		instStaffQueryFormBean.setServiceInstId(this.getCurrentUser().getInstId().toString());
		return this.ucServiceClient.queryInstStaffByServiceId(instStaffQueryFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstStaffFormBean instStaffFormBean, BindingResult bindingResult) {
		if (instStaffFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstStaff(instStaffFormBean);
	}
	
	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		return this.ucServiceClient.deleteInstStaff(id);
	}
	
}
