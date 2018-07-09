package com.sh.carexx.bean.order;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class CustomerOrderScheduleFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Size(max = 20)
	private String orderNo;
	
	@NotBlank
	private String orderAmt;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String serviceStaffId;

	@NotBlank
	private String serviceStartTime;

	@NotBlank
	private String serviceEndTime;

	private String serviceDuration;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String workTypeSettleId;

	private String serviceStatus;

	public Long getId() {
		if (StringUtils.isNotBlank(id)) {
			return Long.parseLong(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	public Integer getServiceStaffId() {
		if (StringUtils.isNotBlank(serviceStaffId)) {
			return Integer.parseInt(serviceStaffId);
		}
		return null;
	}

	public void setServiceStaffId(String serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
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

	public Integer getServiceDuration() {
		if (StringUtils.isNotBlank(serviceDuration)) {
			return Integer.parseInt(serviceDuration);
		}
		return null;
	}

	public void setServiceDuration(String serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public Integer getWorkTypeSettleId() {
		if (StringUtils.isNotBlank(workTypeSettleId)) {
			return Integer.parseInt(workTypeSettleId);
		}
		return null;
	}

	public void setWorkTypeSettleId(String workTypeSettleId) {
		this.workTypeSettleId = workTypeSettleId;
	}

	public Byte getServiceStatus() {
		if (ValidUtils.isInteger(serviceStatus)) {
			return Byte.parseByte(serviceStatus);
		}
		return null;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

}
