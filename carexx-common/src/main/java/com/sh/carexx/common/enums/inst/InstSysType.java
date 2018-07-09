package com.sh.carexx.common.enums.inst;

/**
 * 
 * ClassName: InstSysType <br/> 
 * Function: 定义医护体系类型 <br/> 
 * Reason: 枚举定义 <br/> 
 * Date: 2018年4月24日 下午5:43:53 <br/> 
 * 
 * @author WL  
 * @since JDK 1.8
 */
public enum InstSysType {
	HOSPITAL((byte) 1, "医院"), 
	COMMUNITY((byte) 2, "社区");

	private Byte value;
	private String desc;

	InstSysType(Byte value, String desc) {
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
