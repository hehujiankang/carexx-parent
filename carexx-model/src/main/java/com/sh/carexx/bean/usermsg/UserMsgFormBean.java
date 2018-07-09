package com.sh.carexx.bean.usermsg;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class UserMsgFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String msgType;

	private Integer userId;

	@NotBlank
	@Size(max = 255)
	private String msgTitle;

	@NotBlank
	@Size(max = 255)
	private String msgContent;

	public Integer getId() {
		if (StringUtils.isNotBlank(id)) {
			return Integer.parseInt(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Byte getMsgType() {
		if (ValidUtils.isInteger(msgType)) {
			return Byte.parseByte(msgType);
		}
		return null;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

}
