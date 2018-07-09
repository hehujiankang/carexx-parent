package com.sh.carexx.bean.order;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;

public class ConfirmCompletedOrderFormBean extends BasicFormBean {
	@NotBlank
	private String orderNo;

	@NotBlank
	@Pattern(regexp = CarexxConstant.Regex.INTEGER_POSITIVE)
	private String instSysId;

	@NotBlank
	private String proofType;

	@NotBlank
	@Size(max = 20)
	private String receiptOrInvoice;

	@NotBlank
	@Size(max = 20)
	private String signingPerson;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getInstSysId() {
		if (StringUtils.isNotBlank(instSysId)) {
			return Integer.parseInt(instSysId);
		}
		return null;
	}

	public void setInstSysId(String instSysId) {
		this.instSysId = instSysId;
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

	public String getReceiptOrInvoice() {
		return receiptOrInvoice;
	}

	public void setReceiptOrInvoice(String receiptOrInvoice) {
		this.receiptOrInvoice = receiptOrInvoice;
	}

	public String getSigningPerson() {
		return signingPerson;
	}

	public void setSigningPerson(String signingPerson) {
		this.signingPerson = signingPerson;
	}

}
