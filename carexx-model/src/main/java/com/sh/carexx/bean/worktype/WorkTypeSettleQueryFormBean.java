package com.sh.carexx.bean.worktype;

import com.sh.carexx.bean.BasicFormBean;

public class WorkTypeSettleQueryFormBean extends BasicFormBean {

	private Integer instId;

	private String instName;

	private String workTypeName;

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

}
