package com.sh.carexx.bean.holiday;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class InstHolidayFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String instId;

	@NotBlank
	@Size(max = 20)
	private String holidayName;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DATE)
	private String holidayStartTime;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DATE)
	private String holidayEndTime;

	@Pattern(regexp = "[0,1]")
	private String holidayStatus;

	public Long getId() {
		if (StringUtils.isNotBlank(id)) {
			return Long.parseLong(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayStartTime() {
		return holidayStartTime;
	}

	public void setHolidayStartTime(String holidayStartTime) {
		this.holidayStartTime = holidayStartTime;
	}

	public String getHolidayEndTime() {
		return holidayEndTime;
	}

	public void setHolidayEndTime(String holidayEndTime) {
		this.holidayEndTime = holidayEndTime;
	}

	public String getHolidayStatus() {
		return holidayStatus;
	}

	public void setHolidayStatus(String holidayStatus) {
		this.holidayStatus = holidayStatus;
	}

}
