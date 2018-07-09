package com.sh.carexx.common.enums;

/**
 * 
 * ClassName: TimeUnit <br/>
 * Function: 定义时间单位<br/>
 * Reason: 枚举定义 <br/>
 * Date: 2018年4月24日 下午5:43:53 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public enum TimeUnit {
	HOUR((byte) 1, "小时"), 
	DAY((byte) 2, "天"), 
	WEEK((byte) 3, "周"), 
	MONTH((byte) 4, "月"), 
	YEAR((byte) 5, "年");

	private Byte value;
	private String desc;

	TimeUnit(Byte value, String desc) {
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
