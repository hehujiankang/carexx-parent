package com.sh.carexx.common.enums;

/**
 * 
 * ClassName: TerChnl <br/> 
 * Function: 终端类型定义  <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年4月27日 上午10:09:11 <br/> 
 * 
 * @author WL 
 * @since JDK 1.8
 */
public enum TerChnl {
	PC((byte) 1, "PC网站"), 
	WAP((byte) 2, "WAP网站"), 
	WXWAP((byte) 3, "微信公众号"), 
	WXAPPLET((byte) 4, "微信小程序"), 
	IOS((byte) 5, "IOS移动应用"), 
	ANDROID((byte) 6, "Android移动应用");

	private Byte value;
	private String desc;

	TerChnl(Byte value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
