package com.sh.carexx.model.uc;

import java.util.Date;

public class AclUserMonitor {
	private Integer id;

	private Integer userId;

	private Byte accType;

	private String accIp;

	private Byte accTerChnl;

	private String accTerVer;

	private Date accTime;

	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Byte getAccType() {
		return accType;
	}

	public void setAccType(Byte accType) {
		this.accType = accType;
	}

	public String getAccIp() {
		return accIp;
	}

	public void setAccIp(String accIp) {
		this.accIp = accIp == null ? null : accIp.trim();
	}

	public Byte getAccTerChnl() {
		return accTerChnl;
	}

	public void setAccTerChnl(Byte accTerChnl) {
		this.accTerChnl = accTerChnl;
	}

	public String getAccTerVer() {
		return accTerVer;
	}

	public void setAccTerVer(String accTerVer) {
		this.accTerVer = accTerVer == null ? null : accTerVer.trim();
	}

	public Date getAccTime() {
		return accTime;
	}

	public void setAccTime(Date accTime) {
		this.accTime = accTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}