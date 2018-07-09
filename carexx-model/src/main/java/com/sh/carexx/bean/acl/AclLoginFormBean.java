package com.sh.carexx.bean.acl;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;

public class AclLoginFormBean extends BasicFormBean {
	@NotBlank
	private String acctNo;

	@NotBlank
	@Size(min = 6, max = 20)
	private String loginPass;

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

}
