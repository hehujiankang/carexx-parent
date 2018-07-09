package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerPatient;
import com.sh.carexx.uc.service.CustomerPatientService;

@Service
public class CustomerPatientManager {

	@Autowired
	public CustomerPatientService customerPatientService;

	public void add(CustomerPatientFormBean customerPatientFormBean) throws BizException {
		CustomerPatient customerPatient = new CustomerPatient();
		customerPatient.setCustomerId(customerPatientFormBean.getCustomerId());
		customerPatient.setPatientName(customerPatientFormBean.getPatientName());
		customerPatient.setPhone(customerPatientFormBean.getPhone());
		customerPatient.setAddress(customerPatientFormBean.getAddress());
		customerPatient.setPatientStatus(UseStatus.ENABLED.getValue());

		this.customerPatientService.save(customerPatient);
	}

	public void modify(CustomerPatientFormBean customerPatientFormBean) throws BizException {
		CustomerPatient customerPatient = new CustomerPatient();
		customerPatient.setId(customerPatientFormBean.getId());
		customerPatient.setCustomerId(customerPatientFormBean.getCustomerId());
		customerPatient.setPatientName(customerPatientFormBean.getPatientName());
		customerPatient.setPhone(customerPatientFormBean.getPhone());
		customerPatient.setAddress(customerPatientFormBean.getAddress());

		this.customerPatientService.update(customerPatient);
	}
}
