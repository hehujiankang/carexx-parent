package com.sh.carexx.bean.order;

import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;

public class OrderPaymentFormBean extends BasicFormBean {
	@NotBlank
	private String openId;
	@NotBlank
	private String orderNo;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
