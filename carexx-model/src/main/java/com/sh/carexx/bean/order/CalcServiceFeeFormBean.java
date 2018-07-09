package com.sh.carexx.bean.order;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class CalcServiceFeeFormBean extends BasicFormBean {
	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String instId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String serviceId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DATETIME)
	private String serviceStartTime;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DATETIME)
	private String serviceEndTime;

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

}
