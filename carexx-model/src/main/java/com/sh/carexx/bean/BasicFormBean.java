package com.sh.carexx.bean;

import javax.validation.constraints.Pattern;

import com.sh.carexx.common.util.ValidUtils;

public class BasicFormBean {
	private static final int MIN_PAGE_SIZE = 10;
	private static final int MAX_PAGE_SIZE = 100;
	private static final int EXPORT_SIZE = 65535;
	private static final int DEFAULT_PAGE_NO = 1;

	protected String pageNo;
	protected String pageSize;
	protected String exportSize;

	protected String sortName;
	protected String sortType;
	
	
	protected String ip;
	@Pattern(regexp = "[1,2,3,4,5,6]")
	protected String terChnl;
	protected String terVer;
	protected String terOsVer;
	protected String terDevId;
	protected String terDevName;
	protected String terDevMode;

	protected String token;

	public int getPageNo() {
		int pageNo = DEFAULT_PAGE_NO;
		if (ValidUtils.isInteger(this.pageNo)) {
			pageNo = Integer.parseInt(this.pageNo);
		}
		if (pageNo <= 0) {
			pageNo = DEFAULT_PAGE_NO;
		}
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		int pageSize = MIN_PAGE_SIZE;
		if (ValidUtils.isInteger(this.pageSize)) {
			pageSize = Integer.parseInt(this.pageSize);
		}
		if (pageSize < MIN_PAGE_SIZE) {
			pageSize = MIN_PAGE_SIZE;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public int getExportSize() {
		int exportSize = EXPORT_SIZE;
		if (ValidUtils.isInteger(this.exportSize)) {
			exportSize = Integer.parseInt(this.exportSize);
		}
		if (exportSize > EXPORT_SIZE) {
			exportSize = EXPORT_SIZE;
		}
		return exportSize;
	}

	public void setExportSize(String exportSize) {
		this.exportSize = exportSize;
	}

	public int getRowIndex() {
		return (this.getPageNo() - 1) * this.getPageSize();
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Byte getSortType() {
		if (ValidUtils.isInteger(sortType)) {
			return Byte.parseByte(sortType);
		}
		return null;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Byte getTerChnl() {
		if (ValidUtils.isInteger(terChnl)) {
			return Byte.parseByte(terChnl);
		}
		return null;
	}

	public void setTerChnl(String terChnl) {
		this.terChnl = terChnl;
	}

	public String getTerVer() {
		return terVer;
	}

	public void setTerVer(String terVer) {
		this.terVer = terVer;
	}

	public String getTerOsVer() {
		return terOsVer;
	}

	public void setTerOsVer(String terOsVer) {
		this.terOsVer = terOsVer;
	}

	public String getTerDevId() {
		return terDevId;
	}

	public void setTerDevId(String terDevId) {
		this.terDevId = terDevId;
	}

	public String getTerDevName() {
		return terDevName;
	}

	public void setTerDevName(String terDevName) {
		this.terDevName = terDevName;
	}

	public String getTerDevMode() {
		return terDevMode;
	}

	public void setTerDevMode(String terDevMode) {
		this.terDevMode = terDevMode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
