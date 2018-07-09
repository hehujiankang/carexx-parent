package com.sh.carexx.bean.staff;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class InstStaffFormBean extends BasicFormBean {

	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String id;

	private String instId;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String serviceInstId;

	@NotBlank
	@Pattern(regexp = "[1,2]")
	private String personType;

	@NotBlank
	@Pattern(regexp = "[1,2]")
	private String jobStatus;

	private String staffStatus;

	@NotBlank
	@Size(max = 20)
	private String realName;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.ID_CARD_NO)
	private String idNo;

	@NotBlank
	@Pattern(regexp = "[0,1,2]")
	private String sex;

	@Size(max = 255)
	private String photo;

	@Pattern(regexp = CarexxConstant.Regex.DATE)
	private String birthday;

	@Size(max = 20)
	private String phone;

	@Size(max = 255)
	private String address;

	@Pattern(regexp = CarexxConstant.Regex.DATE)
	private String entryDate;

	@Pattern(regexp = CarexxConstant.Regex.DATE)
	private String leaveDate;
	
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String workTypeId;

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

	public Integer getServiceInstId() {
		if (StringUtils.isNotBlank(serviceInstId)) {
			return Integer.parseInt(serviceInstId);
		}
		return null;
	}

	public void setServiceInstId(String serviceInstId) {
		this.serviceInstId = serviceInstId;
	}

	public Byte getPersonType() {
		if (ValidUtils.isInteger(personType)) {
			return Byte.parseByte(personType);
		}
		return null;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Byte getJobStatus() {
		if (ValidUtils.isInteger(jobStatus)) {
			return Byte.parseByte(jobStatus);
		}
		return null;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Byte getStaffStatus() {
		if (ValidUtils.isInteger(staffStatus)) {
			return Byte.parseByte(staffStatus);
		}
		return null;
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Integer getWorkTypeId() {
		if (StringUtils.isNotBlank(workTypeId)) {
			return Integer.parseInt(workTypeId);
		}
		return null;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}
	

}
