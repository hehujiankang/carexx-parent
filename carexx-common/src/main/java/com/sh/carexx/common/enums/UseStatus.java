package com.sh.carexx.common.enums;

/**
 * 
 * ClassName: UseStatus <br/> 
 * Function: 定义启用状态 <br/> 
 * Reason: 枚举定义 <br/> 
 * Date: 2018年4月24日 下午5:43:53 <br/> 
 * 
 * @author WL  
 * @since JDK 1.8
 */
public enum UseStatus {
	ENABLED((byte) 1, "已启用"), 
	DISABLED((byte) 0, "未启用");

	private Byte value;
	private String desc;

	UseStatus(Byte value, String desc) {
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
