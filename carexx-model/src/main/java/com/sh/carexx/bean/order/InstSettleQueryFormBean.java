package com.sh.carexx.bean.order;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.util.ValidUtils;

public class InstSettleQueryFormBean extends BasicFormBean {

	private Integer instId;

	private String settleStartDate;

	private String settleEndDate;

	private String settleStatus;

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getSettleStartDate() {
		return settleStartDate;
	}

	public void setSettleStartDate(String settleStartDate) {
		this.settleStartDate = settleStartDate;
	}

	public String getSettleEndDate() {
		return settleEndDate;
	}

	public void setSettleEndDate(String settleEndDate) {
		this.settleEndDate = settleEndDate;
	}

	public Byte getSettleStatus() {
		if (ValidUtils.isInteger(settleStatus)) {
			return Byte.parseByte(settleStatus);
		}
		return null;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

}
