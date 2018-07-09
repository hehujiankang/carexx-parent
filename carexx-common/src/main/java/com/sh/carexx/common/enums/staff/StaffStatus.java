package com.sh.carexx.common.enums.staff;

/**
 * 
 * ClassName: JobStatus <br/>
 * Function: 定义员工状态 <br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum StaffStatus {
	DELETED((byte) 0, "已删除"), 
	NORMAL((byte) 1, "正常");

	private Byte value;
	private String desc;

	StaffStatus(Byte value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public byte getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
