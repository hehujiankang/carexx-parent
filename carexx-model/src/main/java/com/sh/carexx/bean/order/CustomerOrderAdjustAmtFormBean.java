package com.sh.carexx.bean.order;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;

public class CustomerOrderAdjustAmtFormBean extends BasicFormBean {

	@NotBlank
	private String orderNo;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DOUBLE)
	private String adjustAmt;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAdjustAmt() {
		return adjustAmt;
	}

	public void setAdjustAmt(String adjustAmt) {
		this.adjustAmt = adjustAmt;
	}
}
