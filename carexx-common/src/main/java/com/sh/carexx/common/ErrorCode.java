package com.sh.carexx.common;

/**
 * 
 * ClassName: ErrorCode <br/> 
 * Function: 定义全局错误码 <br/> 
 * Reason: 定义错误码输出 <br/> 
 * Date: 2018年4月24日 下午5:48:49 <br/> 
 * 
 * @author WL  
 * @since JDK 1.8
 */
public enum ErrorCode {
	SYS_ERROR("E000001", "系统异常"), 
	CACHE_ERROR("E000002", "缓存读写错误"), 
	DB_ERROR("E000003", "数据库读写错误"), 
	ACCESS_DENY("E000004", "无访问权限"), 
	ACCESS_FREQUENCY("E000005", "访问过于频繁"), 
	AUTH_TIMEOUT("E999999", "认证超时"),
	
	SMS_VERIFY_CODE_INVALID("E010101", "验证码已失效"),
	SMS_VERIFY_CODE_INPUT_ERROR("E010102", "验证码错误"),
	
	ACL_REG_ACCT_EXISTS_ERROR("E090101", "登录名已存在"),

	ACL_LOGIN_ACCT_NOT_EXISTS_ERROR("E090201", "账户不存在"), 
	ACL_LOGIN_PASS_ERROR("E090202", "密码输入错误"), 
	ACL_LOGIN_ACCT_LOCKED_ERROR("E090203", "账户已被锁定"),
	ACL_LOGIN_ACCT_CANCELED_ERROR("E090204", "账户已被注销"),
	
	ACL_ROLE_NOT_EXISTS_ERROR("E090301", "角色不存在"), 
	ACL_ROLE_NOT_ENABLED_ERROR("E090302", "角色未启用"),
	
	WORK_TYPE_EXISTS_ERROR("E090401", "工种信息已存在"),
	WORK_TYPE_SETTLE_EXISTS_ERROR("E090402", "工种结算信息已存在"),
	WORK_TYPE_SETTLE_RATIO_INPUT_ERROR("E090403", "工种结算比例输入错误"),
	
	INST_EXISTS_ERROR("E090501", "机构信息已存在"),
	INST_NAME_EXISTS_ERROR("E090502", "机构名称已存在"),
	
	INST_STAFF_EXISTS_ERROR("E090601", "员工信息已存在"),
	IDNO_EXISTS_ERROR("E090602", "身份证号已存在"),
	
	CARE_SERVICE_EXISTS_ERROR("E090701", "平台服务信息已存在"),
	INST_CARE_SERVICE_EXISTS_ERROR("E090702", "机构服务信息已存在"),
	
	STAFF_WORK_TYPE_EXISTS_ERROR("E090801", "该员工的工种已存在"),
	
	ORDER_SCHEDULE_EXISTS_ERROR("E090901", "订单该时段中存在已排班时段"),
	CUSTOMER_ORDER_EXISTS_ERROR("E090902", "客户该时段已预约该服务"),
	TIME_END_BEFORE_START_ERROR("E090903", "所选结束时间必须大于开始时间"),
	ORDER_SETTLE_EXISTS_ERROR("E090904", "该时间段已关账"),
	ADJUST_AMT_GRERTER_ORDER_AMT_ERROR("E090905", "调整金额不合法"),
	
	
	ORDER_PAYMENT_EXISTS_ERROR("E091001", "该订单支付已存在"),
	
	INST_SYS_EXISTS_ERROR("E091101", "公司信息已存在"),
	
	INST_HOLIDAY_EXISTS_ERROR("E091201", "当前机构节假日已存在"),
	
	INST_SETTLE_SETTLE_DATE_GREATER_ERROR("E091301", "账单时间必须大于已添加时间"),
	INST_SETTLE_EXISTS_ERROR("E091302", "当月已关账"),
	SETTLE_AMT_BEYOND_ERROR("E091303", "调整金额已超出"),
	
	INST_INPATIENT_AREA_EXISTS_ERROR("E091401", "当前病区已存在"),
	;

	private String value;
	private String desc;

	ErrorCode(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
