package com.sh.carexx.common.enums.staff;

/**
 * 
 * ClassName: JobStatus <br/>
 * Function: 定义工作状态 <br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum JobStatus {
	ON_JOB((byte) 1, "在职"), 
	LEAVE_JOB((byte) 2, "离职");

	private Byte value;
	private String desc;

	JobStatus(Byte value, String desc) {
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
