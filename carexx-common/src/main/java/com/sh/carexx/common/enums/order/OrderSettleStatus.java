package com.sh.carexx.common.enums.order;

/**
 * 
 * ClassName: OrderSettleStatus <br/>
 * Function: 定义订单结算状态<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum OrderSettleStatus {
	CANCELED((byte) 0, "已取消"), 
	SETTLING((byte) 1, "待结算"), 
	SETTLED((byte) 2, "已结算"), 
	CLOSED((byte) 3, "已关账");

	private Byte value;
	private String desc;

	OrderSettleStatus(Byte value, String desc) {
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
