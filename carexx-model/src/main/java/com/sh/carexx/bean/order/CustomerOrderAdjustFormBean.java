package com.sh.carexx.bean.order;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class CustomerOrderAdjustFormBean extends BasicFormBean {

	@NotBlank
	private String orderNo;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.DOUBLE)
	private String adjustAmt;
	
	@NotBlank 
	private String proofType;

	@NotBlank
	@Size(max = 20)
	private String proofNo;

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
	
	public Byte getProofType() {
		if (ValidUtils.isInteger(proofType)) {
			return Byte.parseByte(proofType);
		}
		return null;
	}

	public void setProofType(String proofType) {
		this.proofType = proofType;
	}

	public String getProofNo() {
		return proofNo;
	}

	public void setProofNo(String proofNo) {
		this.proofNo = proofNo;
	}
}
