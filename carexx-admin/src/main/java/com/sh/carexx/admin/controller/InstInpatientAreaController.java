package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/inpatientarea")
public class InstInpatientAreaController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstInpatientAreaFormBean instInpatientAreaFormBean, BindingResult bindingResult) {
		instInpatientAreaFormBean.setInstId(this.getCurrentUser().getInstId());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstInpatientArea(instInpatientAreaFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		instInpatientAreaFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstInpatientAreaForList(instInpatientAreaFormBean);
	}

	@RequestMapping(value = "/all")
	public String queryForAll() {
		Integer instId = this.getCurrentUser().getInstId();
		return this.ucServiceClient.queryAllInstInpatientArea(instId);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstInpatientAreaFormBean instInpatientAreaFormBean, BindingResult bindingResult) {
		instInpatientAreaFormBean.setInstId(this.getCurrentUser().getInstId());
		if (instInpatientAreaFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstInpatientArea(instInpatientAreaFormBean);
	}

	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		return this.ucServiceClient.deleteInstInpatientArea(id);
	}
}
