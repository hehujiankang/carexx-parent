package com.sh.carexx.bean.care;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class InstInpatientAreaFormBean extends BasicFormBean{

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;
	
	private Integer instId;
	
	@NotBlank
	@Size(max = 20)
	private String inpatientArea;
	
	@Pattern(regexp = "[0,1]")
	private String areaStatus;

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
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getInpatientArea() {
		return inpatientArea;
	}

	public void setInpatientArea(String inpatientArea) {
		this.inpatientArea = inpatientArea;
	}

	public Byte getAreaStatus() {
		if (ValidUtils.isInteger(areaStatus)) {
			return Byte.parseByte(areaStatus);
		}
		return null;
	}

	public void setAreaStatus(String areaStatus) {
		this.areaStatus = areaStatus;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
