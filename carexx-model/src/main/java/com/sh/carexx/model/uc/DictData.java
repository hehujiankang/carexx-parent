package com.sh.carexx.model.uc;

import java.util.Date;

public class DictData {
    private Integer id;

    private Integer dictId;

    private String dictDataName;

    private String dictDataValue;

    private Boolean isFixed;

    private Byte dictDataStatus;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName == null ? null : dictDataName.trim();
    }

    public String getDictDataValue() {
        return dictDataValue;
    }

    public void setDictDataValue(String dictDataValue) {
        this.dictDataValue = dictDataValue == null ? null : dictDataValue.trim();
    }

    public Boolean isFixed() {
        return isFixed;
    }

    public void setFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }

    public Byte getDictDataStatus() {
        return dictDataStatus;
    }

    public void setDictDataStatus(Byte dictDataStatus) {
        this.dictDataStatus = dictDataStatus;
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