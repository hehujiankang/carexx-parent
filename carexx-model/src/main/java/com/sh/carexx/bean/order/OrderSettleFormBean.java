package com.sh.carexx.bean.order;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class OrderSettleFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@DecimalMin(value = CarexxConstant.DecimalMin.MIN_PERCENT)
	private String settleRatio;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String staffId;

	@NotBlank
	@DecimalMin(value = CarexxConstant.DecimalMin.MIN_AMT)
	private String staffSettleAmt;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String settleInstId;

	@NotBlank
	@DecimalMin(value = CarexxConstant.DecimalMin.MIN_AMT)
	private String instSettleAmt;

	public Long getId() {
		if (StringUtils.isNotBlank(id)) {
			return Long.parseLong(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSettleRatio() {
		return settleRatio;
	}

	public void setSettleRatio(String settleRatio) {
		this.settleRatio = settleRatio;
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

	public String getStaffSettleAmt() {
		return staffSettleAmt;
	}

	public void setStaffSettleAmt(String staffSettleAmt) {
		this.staffSettleAmt = staffSettleAmt;
	}

	public Integer getSettleInstId() {
		if (StringUtils.isNotBlank(settleInstId)) {
			return Integer.parseInt(settleInstId);
		}
		return null;
	}

	public void setSettleInstId(String settleInstId) {
		this.settleInstId = settleInstId;
	}

	public String getInstSettleAmt() {
		return instSettleAmt;
	}

	public void setInstSettleAmt(String instSettleAmt) {
		this.instSettleAmt = instSettleAmt;
	}

}
