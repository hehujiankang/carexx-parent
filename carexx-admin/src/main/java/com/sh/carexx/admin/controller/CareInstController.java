package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/careinst")
public class CareInstController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid CareInstFormBean careInstFormBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCareInst(careInstFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(CareInstFormBean careInstFormBean) {
		return this.ucServiceClient.queryCareInstForList(careInstFormBean);
	}

	@RequestMapping(value = "/list_service")
	public String queryServiceForList(CareInstFormBean careInstFormBean) {
		Byte instStatus = UseStatus.ENABLED.getValue();
		careInstFormBean.setInstStatus(instStatus.toString());
		return this.ucServiceClient.queryServiceInstForList(careInstFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid CareInstFormBean careInstFormBean, BindingResult bindingResult) {
		if (careInstFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyCareInst(careInstFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableCareInst(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableCareInst(id);
	}
}
