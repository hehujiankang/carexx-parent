package com.sh.carexx.model.uc;

import java.math.BigDecimal;
import java.util.Date;

public class OrderSettle {
	private Long id;

	private Long scheduleId;

	private BigDecimal settleRatio;

	private Integer staffId;

	private BigDecimal staffSettleAmt;
	
	private BigDecimal adjustAmt;

	private Integer settleInstId;

	private BigDecimal instSettleAmt;

	private Byte settleStatus;

	private Date createTime;

	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public BigDecimal getSettleRatio() {
		return settleRatio;
	}

	public void setSettleRatio(BigDecimal settleRatio) {
		this.settleRatio = settleRatio;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getStaffSettleAmt() {
		return staffSettleAmt;
	}

	public void setStaffSettleAmt(BigDecimal staffSettleAmt) {
		this.staffSettleAmt = staffSettleAmt;
	}
	
	public BigDecimal getAdjustAmt() {
		return adjustAmt;
	}

	public void setAdjustAmt(BigDecimal adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	public Integer getSettleInstId() {
		return settleInstId;
	}

	public void setSettleInstId(Integer settleInstId) {
		this.settleInstId = settleInstId;
	}

	public BigDecimal getInstSettleAmt() {
		return instSettleAmt;
	}

	public void setInstSettleAmt(BigDecimal instSettleAmt) {
		this.instSettleAmt = instSettleAmt;
	}

	public Byte getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(Byte settleStatus) {
		this.settleStatus = settleStatus;
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