package com.sh.carexx.model.uc;

import java.util.Date;

public class CustomerOrderSchedule {
	private Long id;

	private String orderNo;

	private Integer serviceStaffId;

	private Date serviceStartTime;

	private Date serviceEndTime;

	private Integer serviceDuration;

	private Integer workTypeSettleId;

	private Byte serviceStatus;

	private Date createTime;

	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public Integer getServiceStaffId() {
		return serviceStaffId;
	}

	public void setServiceStaffId(Integer serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
	}

	public Date getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(Date serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}

	public Date getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	public Integer getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(Integer serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public Integer getWorkTypeSettleId() {
		return workTypeSettleId;
	}

	public void setWorkTypeSettleId(Integer workTypeSettleId) {
		this.workTypeSettleId = workTypeSettleId;
	}

	public Byte getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Byte serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}