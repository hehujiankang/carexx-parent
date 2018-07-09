package com.sh.carexx.bean.care;

import com.sh.carexx.bean.BasicFormBean;

public class InstServiceQueryFormBean extends BasicFormBean {

	private Integer instId;

	private String serviceName;

	private Integer workTypeId;

	private Byte serviceStatus;

	private Integer mapp;

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}

	public Byte getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(Byte serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Integer getMapp() {
		return mapp;
	}

	public void setMapp(Integer mapp) {
		this.mapp = mapp;
	}

}
