package com.sh.carexx.model.uc;

import java.util.Date;

public class CareInstSys {
    private Integer id;

    private Integer instId;

    private String instSysName;

    private Byte instSysStatus;

    private String introduce;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getInstSysName() {
        return instSysName;
    }

    public void setInstSysName(String instSysName) {
        this.instSysName = instSysName == null ? null : instSysName.trim();
    }

    public Byte getInstSysStatus() {
        return instSysStatus;
    }

    public void setInstSysStatus(Byte instSysStatus) {
        this.instSysStatus = instSysStatus;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}