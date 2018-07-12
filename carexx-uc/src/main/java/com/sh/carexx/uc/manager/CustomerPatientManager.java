package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerPatient;
import com.sh.carexx.uc.service.CustomerPatientService;

/**
 * 
 * ClassName: CustomerPatientManager <br/> 
 * Function: 就诊人信息 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午12:13:26 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class CustomerPatientManager {

	@Autowired
	public CustomerPatientService customerPatientService;

	/**
	 * 
	 * add:(就诊人添加). <br/> 
	 * 
	 * @author zhoulei 
	 * @param customerPatientFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(CustomerPatientFormBean customerPatientFormBean) throws BizException {
		CustomerPatient customerPatient = new CustomerPatient();
		customerPatient.setCustomerId(customerPatientFormBean.getCustomerId());
		customerPatient.setPatientName(customerPatientFormBean.getPatientName());
		customerPatient.setPhone(customerPatientFormBean.getPhone());
		customerPatient.setAddress(customerPatientFormBean.getAddress());
		customerPatient.setPatientStatus(UseStatus.ENABLED.getValue());

		this.customerPatientService.save(customerPatient);
	}

	/**
	 * 
	 * modify:(就诊人修改). <br/> 
	 * 
	 * @author zhoulei 
	 * @param customerPatientFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
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
