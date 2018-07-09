package com.sh.carexx.common.enums.order;

/**
 * 
 * ClassName: OrderType <br/>
 * Function: 定义订单类型<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum OrderType {
	ONLINE_ORDER((byte) 1, "线上订单"), 
	UNDERLINE_ORDER((byte) 2, "线下订单");

	private Byte value;
	private String desc;

	OrderType(Byte value, String desc) {
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
