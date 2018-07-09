package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.customer.InstCustomerFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/instcustomer")
public class InstCustomerController extends BaseController {

	@RequestMapping(value = "/list")
	public String queryForList(InstCustomerFormBean instCustomerFormBean) {
		instCustomerFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		return this.ucServiceClient.queryInstCustomerForList(instCustomerFormBean);
	}

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstCustomerFormBean instCustomerFormBean, BindingResult bindingResult) {
		instCustomerFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstCustomer(instCustomerFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstCustomerFormBean instCustomerFormBean, BindingResult bindingResult) {
		if (instCustomerFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstCustomer(instCustomerFormBean);
	}

	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		return this.ucServiceClient.deleteInstCustomer(id);
	}
}
