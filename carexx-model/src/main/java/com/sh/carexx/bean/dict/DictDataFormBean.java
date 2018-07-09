package com.sh.carexx.bean.dict;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class DictDataFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String dictId;

	@NotBlank
	@Size(max = 20)
	private String dictDataName;

	@NotBlank
	@Size(max = 20)
	private String dictDataValue;

	@Pattern(regexp = "[0,1]")
	private String isFixed;

	private String dictDataStatus;

	public Integer getId() {
		if (StringUtils.isNotBlank(id)) {
			return Integer.parseInt(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDictId() {
		if (StringUtils.isNotBlank(dictId)) {
			return Integer.parseInt(dictId);
		}
		return null;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictDataName() {
		return dictDataName;
	}

	public void setDictDataName(String dictDataName) {
		this.dictDataName = dictDataName;
	}

	public String getDictDataValue() {
		return dictDataValue;
	}

	public void setDictDataValue(String dictDataValue) {
		this.dictDataValue = dictDataValue;
	}

	public Boolean getIsFixed() {
		try {
			return Boolean.parseBoolean(isFixed);
		} catch (Exception e) {
		}
		return null;
	}

	public void setIsFixed(String isFixed) {
		this.isFixed = isFixed;
	}

	public Byte getDictDataStatus() {
		if (ValidUtils.isInteger(dictDataStatus)) {
			return Byte.parseByte(dictDataStatus);
		}
		return null;
	}

	public void setDictDataStatus(String dictDataStatus) {
		this.dictDataStatus = dictDataStatus;
	}
}
