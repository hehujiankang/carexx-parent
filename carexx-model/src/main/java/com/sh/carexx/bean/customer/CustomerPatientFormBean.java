package com.sh.carexx.bean.customer;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class CustomerPatientFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	private String customerId;

	@NotBlank
	@Size(max = 20)
	private String patientName;

	@NotBlank
	@Size(max = 20)
	private String phone;

	@NotBlank
	@Size(max = 255)
	private String address;
	
	private String patientStatus;

	public Long getId() {
		if (StringUtils.isNotBlank(id)) {
			return Long.parseLong(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		if (StringUtils.isNotBlank(customerId)) {
			return Integer.parseInt(customerId);
		}
		return null;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte getPatientStatus() {
		if (ValidUtils.isInteger(patientStatus)) {
			return Byte.parseByte(patientStatus);
		}
		return null;
	}

	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}

	
}
