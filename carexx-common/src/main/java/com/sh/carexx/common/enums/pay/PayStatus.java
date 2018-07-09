package com.sh.carexx.common.enums.pay;

/**
 * 
 * ClassName: PayStatus <br/>
 * Function: 定义支付状态<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum PayStatus {
	PENDING_PAY((byte) 1, "待支付"),
	PAY_SUCCESS((byte) 2, "支付成功"),
	PAY_FAILURE((byte) 3, "支付失败"),
	PAY_TIMEOUT((byte) 9, "支付超时");

	private Byte value;
	private String desc;

	PayStatus(Byte value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Byte getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
