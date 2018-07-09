package com.sh.carexx.bean.customer;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class InstCustomerFormBean extends BasicFormBean {
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	private String instId;

	private String userId;

	@NotBlank
	@Size(max = 20)
	private String realName;

	private String idNo;

	@Pattern(regexp = "[0,1,2]")
	private String sex;

	@Pattern(regexp = CarexxConstant.Regex.DATE)
	private String birthday;

	@Size(max = 20)
	private String phone;

	@Size(max = 225)
	private String address;

	private String customerStatus;

	@Size(max = 20)
	private String linkman;

	@Size(max = 20)
	private String linkmanPhone;

	private String linkmanRelationship;

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

	public Integer getUserId() {
		if (StringUtils.isNotBlank(userId)) {
			return Integer.parseInt(userId);
		}
		return null;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Byte getSex() {
		if (ValidUtils.isInteger(sex)) {
			return Byte.parseByte(sex);
		}
		return null;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte getCustomerStatus() {
		if (ValidUtils.isInteger(customerStatus)) {
			return Byte.parseByte(customerStatus);
		}
		return null;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public Byte getLinkmanRelationship() {
		if (ValidUtils.isInteger(linkmanRelationship)) {
			return Byte.parseByte(linkmanRelationship);
		}
		return null;
	}

	public void setLinkmanRelationship(String linkmanRelationship) {
		this.linkmanRelationship = linkmanRelationship;
	}
}
