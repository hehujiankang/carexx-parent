package com.sh.carexx.bean.acl;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;

public class AclModifyPwdFormBean extends BasicFormBean {

	private Integer userId;

	@NotBlank
	@Size(min = 6, max = 20)
	private String oldPwd;

	@NotBlank
	@Size(min = 6, max = 20)
	private String newPwd;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
