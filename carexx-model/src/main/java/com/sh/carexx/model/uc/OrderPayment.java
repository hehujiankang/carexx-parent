package com.sh.carexx.model.uc;

import java.math.BigDecimal;
import java.util.Date;

public class OrderPayment {
    private Long id;

    private String orderNo;

    private Byte payType;

    private BigDecimal payAmt;

    private Byte payStatus;

    private Byte payChnl;

    private String payChnlTransNo;

    private Date payTime;

    private Byte reconFlag;

    private Date reconTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getPayChnl() {
        return payChnl;
    }

    public void setPayChnl(Byte payChnl) {
        this.payChnl = payChnl;
    }

    public String getPayChnlTransNo() {
        return payChnlTransNo;
    }

    public void setPayChnlTransNo(String payChnlTransNo) {
        this.payChnlTransNo = payChnlTransNo == null ? null : payChnlTransNo.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getReconFlag() {
        return reconFlag;
    }

    public void setReconFlag(Byte reconFlag) {
        this.reconFlag = reconFlag;
    }

    public Date getReconTime() {
        return reconTime;
    }

    public void setReconTime(Date reconTime) {
        this.reconTime = reconTime;
    }
}