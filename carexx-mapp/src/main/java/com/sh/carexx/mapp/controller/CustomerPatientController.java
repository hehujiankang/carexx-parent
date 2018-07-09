package com.sh.carexx.mapp.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/customerpatient")
public class CustomerPatientController extends BaseController {

	@RequestMapping(value = "/list")
	public String queryForList(CustomerPatientFormBean customerPatientFormBean) {
		customerPatientFormBean.setCustomerId(this.getCurrentUser().getId().toString());
		return this.ucServiceClient.queryCustomerPatientForList(customerPatientFormBean);
	}

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid CustomerPatientFormBean customerPatientFormBean, BindingResult bindingResult) {
		customerPatientFormBean.setCustomerId(this.getCurrentUser().getId().toString());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCustomerPatient(customerPatientFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid CustomerPatientFormBean customerPatientFormBean, BindingResult bindingResult) {
		customerPatientFormBean.setCustomerId(this.getCurrentUser().getId().toString());
		if (customerPatientFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyCustomerPatient(customerPatientFormBean);
	}

	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Long id) {
		Integer customerId = this.getCurrentUser().getId();
		return this.ucServiceClient.deleteCustomerPatient(id,customerId);
	}
}
