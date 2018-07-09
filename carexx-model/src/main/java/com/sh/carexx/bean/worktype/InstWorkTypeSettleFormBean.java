package com.sh.carexx.bean.worktype;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class InstWorkTypeSettleFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	private String instId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String workTypeId;

	@NotBlank
	@DecimalMin(value = CarexxConstant.DecimalMin.MIN_PERCENT)
	private String settleRatio;

	private String settleStatus;

	public Integer getId() {
		if (StringUtils.isNotBlank(id)) {
			return Integer.parseInt(id);
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

	public Integer getWorkTypeId() {
		if (StringUtils.isNotBlank(workTypeId)) {
			return Integer.parseInt(workTypeId);
		}
		return null;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getSettleRatio() {
		return settleRatio;
	}

	public void setSettleRatio(String settleRatio) {
		this.settleRatio = settleRatio;
	}

	public Byte getSettleStatus() {
		if (StringUtils.isNotBlank(settleStatus)) {
			return Byte.parseByte(settleStatus);
		}
		return null;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

}
