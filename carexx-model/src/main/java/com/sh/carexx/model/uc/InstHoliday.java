package com.sh.carexx.model.uc;

import java.util.Date;

public class InstHoliday {
	private Long id;

	private Integer instId;

	private String holidayName;

	private Date holidayStartTime;

	private Date holidayEndTime;

	private Byte holidayStatus;

	private Date createTime;

	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName == null ? null : holidayName.trim();
	}

	public Date getHolidayStartTime() {
		return holidayStartTime;
	}

	public void setHolidayStartTime(Date holidayStartTime) {
		this.holidayStartTime = holidayStartTime;
	}

	public Date getHolidayEndTime() {
		return holidayEndTime;
	}

	public void setHolidayEndTime(Date holidayEndTime) {
		this.holidayEndTime = holidayEndTime;
	}

	public Byte getHolidayStatus() {
		return holidayStatus;
	}

	public void setHolidayStatus(Byte holidayStatus) {
		this.holidayStatus = holidayStatus;
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