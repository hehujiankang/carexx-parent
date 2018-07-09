package com.sh.carexx.common.enums.order;

/**
 * 
 * ClassName: PayChnl <br/>
 * Function: 定义订单状态<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum OrderStatus {
	WAIT_SCHEDULE((byte) 1, "待排班"),
	CANCELED((byte) 2, "已取消"),
	IN_SERVICE((byte) 3, "服务中"), 
	WAIT_PAY((byte) 4, "待支付"), 
	ALREADY_PAY((byte) 5, "已支付"), 
	COMPLETED((byte) 6, "已完成");

	private Byte value;
	private String desc;

	OrderStatus(Byte value, String desc) {
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
