package com.sh.carexx.model.uc;

import java.math.BigDecimal;
import java.util.Date;

public class InstWorkTypeSettle {
    private Integer id;

    private Integer instId;

    private Integer workTypeId;

    private BigDecimal settleRatio;

    private Byte settleStatus;

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

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public BigDecimal getSettleRatio() {
        return settleRatio;
    }

    public void setSettleRatio(BigDecimal settleRatio) {
        this.settleRatio = settleRatio;
    }

    public Byte getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Byte settleStatus) {
        this.settleStatus = settleStatus;
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