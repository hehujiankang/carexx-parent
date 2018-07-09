package com.sh.carexx.common.enums.pay;

/**
 * 
 * ClassName: PayChnl <br/>
 * Function: 定义支付渠道<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum PayChnl {
	BALANCE((byte) 1, "余额支付"), 
	ALIPAY((byte) 2, "支付宝支付"), 
	WECHATPAY((byte) 3, "微信支付");

	private Byte value;
	private String desc;

	PayChnl(Byte value, String desc) {
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
