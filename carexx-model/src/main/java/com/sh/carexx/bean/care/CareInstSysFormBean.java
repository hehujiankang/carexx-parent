package com.sh.carexx.bean.care;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class CareInstSysFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;
	
	private String instId;
	
	@NotBlank
	@Size(max = 20)
	private String instSysName;
	
	@Pattern(regexp = "[1,2]")
	private String instSysStatus;
	
	@Size(max = 255)
	private String introduce;

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

	public String getInstSysName() {
		return instSysName;
	}

	public void setInstSysName(String instSysName) {
		this.instSysName = instSysName;
	}

	public Byte getInstSysStatus() {
		if (ValidUtils.isInteger(instSysStatus)) {
			return Byte.parseByte(instSysStatus);
		}
		return null;
	}

	public void setInstSysStatus(String instSysStatus) {
		this.instSysStatus = instSysStatus;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
	
}
