package com.sh.carexx.model.uc;

import java.util.Date;
import java.util.List;

public class AclMenu {
	private Integer id;

	private String code;

	private String name;

	private String visitUrl;

	private Integer parentId;

	private Byte status;

	private Byte level;

	private Integer sortId;

	private String remark;

	private Date createTime;

	private Date modifyTime;

	private List<AclMenu> subMenuList;

	private List<AclMenuOper> operList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getVisitUrl() {
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl == null ? null : visitUrl.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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

	public List<AclMenu> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<AclMenu> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public List<AclMenuOper> getOperList() {
		return operList;
	}

	public void setOperList(List<AclMenuOper> operList) {
		this.operList = operList;
	}

}