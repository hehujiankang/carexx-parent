package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerPatient;
import com.sh.carexx.uc.dao.CustomerPatientMapper;
import com.sh.carexx.uc.service.CustomerPatientService;

@Service
public class CustomerPatientServiceImpl implements CustomerPatientService {

	@Autowired
	private CustomerPatientMapper customerPatientMapper;

	@Override
	public void save(CustomerPatient customerPatient) throws BizException {
		int rows = 0;
		try {
			rows = this.customerPatientMapper.insert(customerPatient);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getCustomerPatientCount(CustomerPatientFormBean customerPatientFormBean) {
		return customerPatientMapper.selectCustomerPatientCount(customerPatientFormBean);
	}

	@Override
	public List<Map<?, ?>> queryCustomerPatientList(CustomerPatientFormBean customerPatientFormBean) {
		return customerPatientMapper.selectCustomerPatientList(customerPatientFormBean);
	}

	@Override
	public void update(CustomerPatient customerPatient) throws BizException {
		int rows = 0;
		try {
			rows = this.customerPatientMapper.update(customerPatient);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void delete(Long id, Integer customerId) throws BizException {
		int rows = 0;
		try {
			rows = this.customerPatientMapper.delete(id, customerId);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}

	}

}
