package com.sh.carexx.common.enums.order;

/**
 * 
 * ClassName: ProofType <br/> 
 * Function: 定义凭证类型<br/> 
 * Reason: 枚举定义 <br/> 
 * Date: 2018年6月8日 下午2:34:16 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
public enum ProofType {
	RECEIPT((byte) 1, "收据"), 
	INVOICE((byte) 2, "发票");

	private Byte value;
	private String desc;

	ProofType(Byte value, String desc) {
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
