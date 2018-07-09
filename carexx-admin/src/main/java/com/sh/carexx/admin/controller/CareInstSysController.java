package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.CareInstSysFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/careinstsys")
public class CareInstSysController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid CareInstSysFormBean careInstSysFormBean, BindingResult bindingResult) {
		careInstSysFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCareInstSys(careInstSysFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(CareInstSysFormBean careInstSysFormBean) {
		careInstSysFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		return this.ucServiceClient.queryCareInstSysForList(careInstSysFormBean);
	}

	@RequestMapping(value = "/list_all")
	public String queryAllAvailable() {
		Integer instId = this.getCurrentUser().getInstId();
		return this.ucServiceClient.queryAllAvailableCareInstSys(instId);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableCareInstSys(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableCareInstSys(id);
	}
}
