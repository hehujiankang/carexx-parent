package com.sh.carexx.common.enums.acl;

/**
 * 
 * ClassName: 后台账户密码状态 <br/>
 * Function: TODO <br/>
 * Date: 2017年3月9日 上午11:17:08 <br/>
 *
 * @author wanglong
 * @version
 * @since JDK 1.7
 */
public enum AclUserPwdStatus {
	INITIAL((byte) 0, "初始密码"), 
	NON_INITIAL((byte) 1, "非初始密码");

	private byte value;
	private String desc;

	AclUserPwdStatus(byte value, String desc) {
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
