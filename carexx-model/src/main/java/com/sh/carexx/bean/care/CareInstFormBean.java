package com.sh.carexx.bean.care;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class CareInstFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Pattern(regexp = "[1,2]")
	private String instType;

	private String instStatus;

	@NotBlank
	@Size(max = 50)
	private String instName;

	@Size(max = 100)
	private String instRegion;

	@Size(max = 255)
	private String instAddr;

	@NotBlank
	@DecimalMin(value = CarexxConstant.DecimalMin.ZERO)
	private String instLng;

	@NotBlank
	@DecimalMin(value = CarexxConstant.DecimalMin.ZERO)
	private String instLat;

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

	public Byte getInstType() {
		if (ValidUtils.isInteger(instType)) {
			return Byte.parseByte(instType);
		}
		return null;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

	public Byte getInstStatus() {
		if (ValidUtils.isInteger(instStatus)) {
			return Byte.parseByte(instStatus);
		}
		return null;
	}

	public void setInstStatus(String instStatus) {
		this.instStatus = instStatus;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstRegion() {
		return instRegion;
	}

	public void setInstRegion(String instRegion) {
		this.instRegion = instRegion;
	}

	public String getInstAddr() {
		return instAddr;
	}

	public void setInstAddr(String instAddr) {
		this.instAddr = instAddr;
	}

	public String getInstLng() {
		return instLng;
	}

	public void setInstLng(String instLng) {
		this.instLng = instLng;
	}

	public String getInstLat() {
		return instLat;
	}

	public void setInstLat(String instLat) {
		this.instLat = instLat;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

}
