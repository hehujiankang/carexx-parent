package com.sh.carexx.bean.acl;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class AclRoleFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Size(max = 50)
	private String name;

	@NotBlank
	@Size(max = 255)
	private String remark;

	private String status;

	private Integer creator;

	public Integer getId() {
		if (StringUtils.isNotBlank(id)) {
			return Integer.parseInt(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		if (ValidUtils.isInteger(status)) {
			return Byte.parseByte(status);
		}
		return null;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

}
