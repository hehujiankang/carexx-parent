package com.sh.carexx.model.uc;

import java.util.Date;

public class InstInpatientArea {
    private Integer id;

    private Integer instId;

    private String inpatientArea;

    private Byte areaStatus;

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

    public String getInpatientArea() {
        return inpatientArea;
    }

    public void setInpatientArea(String inpatientArea) {
        this.inpatientArea = inpatientArea == null ? null : inpatientArea.trim();
    }

    public Byte getAreaStatus() {
        return areaStatus;
    }

    public void setAreaStatus(Byte areaStatus) {
        this.areaStatus = areaStatus;
    }

    public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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