package com.sh.carexx.common.enums.order;

/**
 * 
 * ClassName: OrderScheduleStatus <br/>
 * Function: 定义订单排班状态<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum OrderScheduleStatus {
	DELETED((byte) 0, "已删除"), 
	IN_SERVICE((byte) 1, "服务中"),
	COMPLETED((byte) 2, "已完成");

	private Byte value;
	private String desc;

	OrderScheduleStatus(Byte value, String desc) {
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
