package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.CareServiceFormBean;
import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/careservice")
public class CareServiceController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid CareServiceFormBean careServiceFormBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCareService(careServiceFormBean);
	}

	@RequestMapping(value = "/list_all")
	public String queryAllCareService() {
		return this.ucServiceClient.queryAllCareService();
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstServiceQueryFormBean instServiceQueryFormBean) {
		return this.ucServiceClient.queryCareServiceForList(instServiceQueryFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid CareServiceFormBean careServiceFormBean, BindingResult bindingResult) {
		if (careServiceFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyCareService(careServiceFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableCareService(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableCareService(id);
	}

	@RequestMapping(value = "/add_inst")
	public BasicRetVal addInstCareService(@Valid InstCareServiceFormBean instCareServiceFormBean,
			BindingResult bindingResult) {
		instCareServiceFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (instCareServiceFormBean.getServiceId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstCareService(instCareServiceFormBean);
	}

	@RequestMapping(value = "/modify_inst")
	public BasicRetVal modifyInstCareService(@Valid InstCareServiceFormBean instCareServiceFormBean,
			BindingResult bindingResult) {
		if (instCareServiceFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstCareService(instCareServiceFormBean);
	}

	@RequestMapping(value = "/list_all_inst")
	public String queryAllAvailableInstCareService(InstServiceQueryFormBean instServiceQueryFormBean) {
		instServiceQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryAllAvailableInstCareService(instServiceQueryFormBean);
	}

	@RequestMapping(value = "/list_inst")
	public String queryInstCareServiceForList(InstServiceQueryFormBean instServiceQueryFormBean) {
		instServiceQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstCareServiceForList(instServiceQueryFormBean);
	}

	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal deleteInstCareService(@PathVariable("id") Integer id) {
		return this.ucServiceClient.deleteInstCareService(id);
	}
}
