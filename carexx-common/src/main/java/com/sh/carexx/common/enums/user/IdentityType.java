package com.sh.carexx.common.enums.user;

/**
 * 
 * ClassName: IdentityType <br/>
 * Function: 定义授权类型 <br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum IdentityType {
	WECHAT((byte) 1, "微信授权"), 
	QQ((byte) 2, "QQ授权"), 
	SINA((byte) 3, "新浪微博授权");

	private Byte value;
	private String desc;

	IdentityType(Byte value, String desc) {
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
