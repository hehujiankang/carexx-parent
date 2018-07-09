package com.sh.carexx.common.enums;

/**
 * 
 * ClassName: Sex <br/>
 * Function: 定义性别 <br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum Sex {
	UNKNOWN((byte) 0, "未知"), 
	MALE((byte) 1, "男"), 
	FEMALE((byte) 2, "女");

	private Byte value;
	private String desc;

	Sex(Byte value, String desc) {
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
