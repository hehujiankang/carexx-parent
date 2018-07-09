package com.sh.carexx.model.uc;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerOrder {
	private Long id;

	private Byte orderType;

	private Integer userId;

	private Integer customerId;

	private Integer instId;

	private Integer serviceId;

	private String orderNo;

	private BigDecimal orderAmt;

	private BigDecimal adjustAmt;

	private Integer inpatientAreaId;

	private String inpatientWard;

	private Date serviceStartTime;

	private Date serviceEndTime;
	
	private BigDecimal holiday;

	private Byte orderStatus;

	private String operator;

	private String orderRemark;

	private Integer instSysId;

	private Byte proofType;

	private String receiptNo;

	private String invoiceNo;

	private String signingPerson;

	private Date createTime;

	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getOrderType() {
		return orderType;
	}

	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public BigDecimal getAdjustAmt() {
		return adjustAmt;
	}

	public void setAdjustAmt(BigDecimal adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	public Integer getInpatientAreaId() {
		return inpatientAreaId;
	}

	public void setInpatientAreaId(Integer inpatientAreaId) {
		this.inpatientAreaId = inpatientAreaId;
	}

	public String getInpatientWard() {
		return inpatientWard;
	}

	public void setInpatientWard(String inpatientWard) {
		this.inpatientWard = inpatientWard == null ? null : inpatientWard.trim();
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

	public BigDecimal getHoliday() {
		return holiday;
	}

	public void setHoliday(BigDecimal holiday) {
		this.holiday = holiday;
	}

	public Byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark == null ? null : orderRemark.trim();
	}

	public Integer getInstSysId() {
		return instSysId;
	}

	public void setInstSysId(Integer instSysId) {
		this.instSysId = instSysId;
	}

	public Byte getProofType() {
		return proofType;
	}

	public void setProofType(Byte proofType) {
		this.proofType = proofType;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo == null ? null : receiptNo.trim();
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
	}

	public String getSigningPerson() {
		return signingPerson;
	}

	public void setSigningPerson(String signingPerson) {
		this.signingPerson = signingPerson;
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