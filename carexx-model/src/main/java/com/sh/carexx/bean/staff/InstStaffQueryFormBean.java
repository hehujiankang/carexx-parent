package com.sh.carexx.bean.staff;

import org.apache.commons.lang.StringUtils;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.util.ValidUtils;

public class InstStaffQueryFormBean extends BasicFormBean {

	private Integer instId;

	private String serviceInstId;

	private String serviceId;

	private String personType;

	private String jobStatus;

	private String workTypeId;

	private String realName;

	private String idNo;

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Integer getServiceInstId() {
		if (StringUtils.isNotBlank(serviceInstId)) {
			return Integer.parseInt(serviceInstId);
		}
		return null;
	}

	public void setServiceInstId(String serviceInstId) {
		this.serviceInstId = serviceInstId;
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

	public Byte getPersonType() {
		if (ValidUtils.isInteger(personType)) {
			return Byte.parseByte(personType);
		}
		return null;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Byte getJobStatus() {
		if (ValidUtils.isInteger(jobStatus)) {
			return Byte.parseByte(jobStatus);
		}
		return null;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Integer getWorkTypeId() {
		if (StringUtils.isNotBlank(workTypeId)) {
			return Integer.parseInt(workTypeId);
		}
		return null;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

}
