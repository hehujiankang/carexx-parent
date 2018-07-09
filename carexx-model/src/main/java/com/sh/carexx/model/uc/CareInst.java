package com.sh.carexx.model.uc;

import java.util.Date;

public class CareInst {
    private Integer id;

    private Byte instType;

    private String instName;

    private Byte instStatus;

    private String instRegion;

    private String instAddr;

    private Double instLng;

    private Double instLat;

    private String introduce;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getInstType() {
        return instType;
    }

    public void setInstType(Byte instType) {
        this.instType = instType;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }

    public Byte getInstStatus() {
        return instStatus;
    }

    public void setInstStatus(Byte instStatus) {
        this.instStatus = instStatus;
    }

    public String getInstRegion() {
        return instRegion;
    }

    public void setInstRegion(String instRegion) {
        this.instRegion = instRegion == null ? null : instRegion.trim();
    }

    public String getInstAddr() {
        return instAddr;
    }

    public void setInstAddr(String instAddr) {
        this.instAddr = instAddr == null ? null : instAddr.trim();
    }

    public Double getInstLng() {
        return instLng;
    }

    public void setInstLng(Double instLng) {
        this.instLng = instLng;
    }

    public Double getInstLat() {
        return instLat;
    }

    public void setInstLat(Double instLat) {
        this.instLat = instLat;
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