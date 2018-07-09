package com.sh.carexx.common.enums.acl;

/**
 * 
 * ClassName: 登录类型 <br/>
 * Function: TODO <br/>
 * Date: 2017年3月9日 上午11:17:08 <br/>
 *
 * @author wanglong
 * @version
 * @since JDK 1.7
 */
public enum AclUserMonitorType {
	LOGIN((byte) 1, "登录"), 
	MODIFY_PWD((byte) 2, "修改密码");

	private Byte value;
	private String desc;

	AclUserMonitorType(Byte value, String desc) {
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
