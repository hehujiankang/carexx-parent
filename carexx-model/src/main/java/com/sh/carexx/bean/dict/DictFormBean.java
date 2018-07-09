package com.sh.carexx.bean.dict;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class DictFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Size(max = 20)
	private String dictName;

	@NotBlank
	@Size(max = 20)
	private String dictValue;

	private String dictStatus;

	public Integer getId() {
		if (StringUtils.isNotBlank(id)) {
			return Integer.parseInt(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Byte getDictStatus() {
		if (ValidUtils.isInteger(dictStatus)) {
			return Byte.parseByte(dictStatus);
		}
		return null;
	}

	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}

}
