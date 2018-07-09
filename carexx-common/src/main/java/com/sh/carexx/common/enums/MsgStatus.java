package com.sh.carexx.common.enums;

/**
 * 
 * ClassName: MsgStatus <br/>
 * Function: 定义消息状态 <br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum MsgStatus {
	UNREAD((byte) 0, "未读"), 
	READ((byte) 1, "已读"), 
	DELETED((byte) 2, "已删除");

	private Byte value;
	private String desc;

	MsgStatus(Byte value, String desc) {
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
