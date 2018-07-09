package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.worktype.WorkTypeFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/worktype")
public class WorkTypeController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid WorkTypeFormBean workTypeFormBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addWorkType(workTypeFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid WorkTypeFormBean workTypeFormBean, BindingResult bindingResult) {
		if (workTypeFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyWorkType(workTypeFormBean);
	}

	@RequestMapping(value = "/list_all")
	public String queryAllAvailable() {
		return this.ucServiceClient.queryAllAvailableWorkType();
	}

	@RequestMapping(value = "/list")
	public String queryForList(WorkTypeFormBean workTypeFormBean) {
		return this.ucServiceClient.queryForListWorkType(workTypeFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableWorkType(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableWorkType(id);
	}
}
