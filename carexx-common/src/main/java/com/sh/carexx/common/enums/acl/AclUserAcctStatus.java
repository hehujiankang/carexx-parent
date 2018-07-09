package com.sh.carexx.common.enums.acl;

/**
 * 
 * ClassName: 管理账户状态 <br/>
 * Function: TODO <br/>
 * Date: 2017年3月9日 上午11:17:08 <br/>
 *
 * @author wanglong
 * @version
 * @since JDK 1.7
 */
public enum AclUserAcctStatus {
	NORMAL((byte) 1, "正常"), 
	LOCKED((byte) 2, "已冻结"), 
	CANCELED((byte) 3, "已作废");

	private byte value;
	private String desc;

	AclUserAcctStatus(byte value, String desc) {
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
