package com.sh.carexx.bean.order;

import org.apache.commons.lang.StringUtils;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.util.ValidUtils;

public class CustomerOrderQueryFormBean extends BasicFormBean {

	private Integer instId;

	private String orderType;

	private String orderNo;

	private Integer userId;

	private String customerId;

	private String staffName;

	private String serviceId;

	private String realName;

	private String workTypeId;

	private String workTypeName;

	private String serviceName;

	private String serviceStartTime;

	private String serviceEndTime;

	private String orderStatus;

	private String selectType;

	private String payType;

	private String instSysId;

	private String inpatientAreaId;

	private String proofType;

	private String receiptOrInvoice;

	private String signingPerson;

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Byte getOrderType() {
		if (ValidUtils.isInteger(orderType)) {
			return Byte.parseByte(orderType);
		}
		return null;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public Byte getOrderStatus() {
		if (ValidUtils.isInteger(orderStatus)) {
			return Byte.parseByte(orderStatus);
		}
		return null;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public Byte getPayType() {
		if (ValidUtils.isInteger(payType)) {
			return Byte.parseByte(payType);
		}
		return null;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public Integer getInpatientAreaId() {
		if (StringUtils.isNotBlank(inpatientAreaId)) {
			return Integer.parseInt(inpatientAreaId);
		}
		return null;
	}

	public void setInpatientAreaId(String inpatientAreaId) {
		this.inpatientAreaId = inpatientAreaId;
	}

	public Byte getProofType() {
		if (ValidUtils.isInteger(proofType)) {
			return Byte.parseByte(proofType);
		}
		return null;
	}

	public void setProofType(String proofType) {
		this.proofType = proofType;
	}

	public String getReceiptOrInvoice() {
		return receiptOrInvoice;
	}

	public void setReceiptOrInvoice(String receiptOrInvoice) {
		this.receiptOrInvoice = receiptOrInvoice;
	}

	public String getSigningPerson() {
		return signingPerson;
	}

	public void setSigningPerson(String signingPerson) {
		this.signingPerson = signingPerson;
	}

}
