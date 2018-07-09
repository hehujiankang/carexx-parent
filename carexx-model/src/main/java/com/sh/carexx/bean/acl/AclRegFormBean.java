package com.sh.carexx.bean.acl;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;

public class AclRegFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = CarexxConstant.Regex.WORD_NUM)
	private String account;

	@Size(min = 6, max = 20)
	private String loginPass;

	@NotBlank
	@Size(max = 20)
	private String name;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.MOBILE)
	private String mobile;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.EMAIL)
	private String email;

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String instId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String roleId;

	private String status;

	private String startRegDate;

	private String endRegDate;

	public Integer getId() {
		if (ValidUtils.isInteger(id)) {
			return Integer.parseInt(id);
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getInstId() {
		if (ValidUtils.isInteger(instId)) {
			return Integer.parseInt(instId);
		}
		return null;
	}

	public void setInstId(String instId) {
		this.instId = instId;
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

	public Integer getRoleId() {
		if (ValidUtils.isInteger(roleId)) {
			return Integer.parseInt(roleId);
		}
		return null;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getStartRegDate() {
		if (ValidUtils.isDate(startRegDate)) {
			return DateUtils.toDate(startRegDate, DateUtils.YYYY_MM_DD);
		}
		return null;
	}

	public void setStartRegDate(String startRegDate) {
		this.startRegDate = startRegDate;
	}

	public Date getEndRegDate() {
		if (ValidUtils.isDate(endRegDate)) {
			return DateUtils.toDate(endRegDate, DateUtils.YYYY_MM_DD);
		}
		return null;
	}

	public void setEndRegDate(String endRegDate) {
		this.endRegDate = endRegDate;
	}
}
