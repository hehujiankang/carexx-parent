package com.sh.carexx.model.uc;

import java.util.Date;

public class WorkType {
    private Integer id;

    private String workTypeName;

    private Byte workTypeStatus;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName == null ? null : workTypeName.trim();
    }

    public Byte getWorkTypeStatus() {
        return workTypeStatus;
    }

    public void setWorkTypeStatus(Byte workTypeStatus) {
        this.workTypeStatus = workTypeStatus;
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