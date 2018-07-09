package com.sh.carexx.bean.staff;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class InstStaffWorkTypeFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String staffId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String workTypeId;

	private String settleStatus;

	public Long getId() {
		if (StringUtils.isNotBlank(id)) {
			return Long.parseLong(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStaffId() {
		if (StringUtils.isNotBlank(staffId)) {
			return Integer.parseInt(staffId);
		}
		return null;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
