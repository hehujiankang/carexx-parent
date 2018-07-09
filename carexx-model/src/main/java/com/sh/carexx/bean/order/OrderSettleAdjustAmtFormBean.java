package com.sh.carexx.bean.order;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class OrderSettleAdjustAmtFormBean extends BasicFormBean {

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String scheduleId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DOUBLE)
	private String adjustAmt;

	public Long getScheduleId() {
		if (StringUtils.isNotBlank(scheduleId)) {
			return Long.parseLong(scheduleId);
		}
		return null;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getAdjustAmt() {
		return adjustAmt;
	}

	public void setAdjustAmt(String adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

}
