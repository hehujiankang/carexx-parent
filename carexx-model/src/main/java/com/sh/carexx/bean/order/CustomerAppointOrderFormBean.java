package com.sh.carexx.bean.order;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class CustomerAppointOrderFormBean extends BasicFormBean {

	private String customerId;

	@NotBlank
	private String patientName;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String instId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String serviceId;

	@DecimalMin(value = CarexxConstant.DecimalMin.ZERO)
	private String adjustAmt;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String inpatientAreaId;

	@NotBlank
	@Size(max = 20)
	private String inpatientWard;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DATETIME)
	private String serviceStartTime;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DATETIME)
	private String serviceEndTime;

	@Size(max = 255)
	private String orderRemark;

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

	public Integer getInstId() {
		if (StringUtils.isNotBlank(instId)) {
			return Integer.parseInt(instId);
		}
		return null;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public Integer getServiceId() {
		if (StringUtils.isNotBlank(serviceId)) {
			return Integer.parseInt(serviceId);
		}
		return null;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getAdjustAmt() {
		return adjustAmt;
	}

	public void setAdjustAmt(String adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	public Integer getInpatientAreaId() {
		if (StringUtils.isNotBlank(inpatientAreaId)) {
			return Integer.parseInt(inpatientAreaId);
		}
		return null;
	}

	public void setInpatientAreaId(String inpatientAreaId) {
		this.inpatientAreaId = inpatientAreaId;
	}

	public String getInpatientWard() {
		return inpatientWard;
	}

	public void setInpatientWard(String inpatientWard) {
		this.inpatientWard = inpatientWard;
	}

	public String getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(String serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}

	public String getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

}
