package com.sh.carexx.bean.order;

import org.apache.commons.lang.StringUtils;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.util.ValidUtils;

public class WorkQuantityReportFormBean extends BasicFormBean {

	private String serviceStartTime;

	private String serviceEndTime;

	private String instSysId;

	private Integer instId;

	private String personType;

	private String workTypeId;

	private String staffName;

	private String patientName;

	private String settleStatus;

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

	public Integer getInstSysId() {
		if (StringUtils.isNotBlank(instSysId)) {
			return Integer.parseInt(instSysId);
		}
		return null;
	}

	public void setInstSysId(String instSysId) {
		this.instSysId = instSysId;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Integer getPersonType() {
		if (StringUtils.isNotBlank(personType)) {
			return Integer.parseInt(personType);
		}
		return null;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Byte getSettleStatus() {
		if (ValidUtils.isInteger(settleStatus)) {
			return Byte.parseByte(settleStatus);
		}
		return null;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
}
