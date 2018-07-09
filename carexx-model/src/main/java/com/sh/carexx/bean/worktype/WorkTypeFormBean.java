package com.sh.carexx.bean.worktype;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class WorkTypeFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Size(max = 20)
	private String workTypeName;

	private String workTypeStatus;

	public Integer getId() {
		if (StringUtils.isNotBlank(id)) {
			return Integer.parseInt(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public Byte getWorkTypeStatus() {
		if (ValidUtils.isInteger(workTypeStatus)) {
			return Byte.parseByte(workTypeStatus);
		}
		return null;
	}

	public void setWorkTypeStatus(String workTypeStatus) {
		this.workTypeStatus = workTypeStatus;
	}

}
