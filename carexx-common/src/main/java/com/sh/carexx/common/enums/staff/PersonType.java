package com.sh.carexx.common.enums.staff;

/**
 * 
 * ClassName: PersonType <br/>
 * Function: 定义人员性质 <br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum PersonType {
	FULLTIME((byte) 1, "在编"), 
	PARTIME((byte) 2, "加盟");

	private Byte value;
	private String desc;

	PersonType(Byte value, String desc) {
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
