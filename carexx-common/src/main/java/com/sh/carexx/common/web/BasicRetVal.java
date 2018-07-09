package com.sh.carexx.common.web;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.util.JSONUtils;

/**
 * 
 * ClassName: BasicRetVal <br/>
 * Function: 接口基本输出模型（不带业务数据） <br/>
 * Reason: 定义输出模型 <br/>
 * Date: 2018年4月24日 下午5:46:42 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public class BasicRetVal {

	protected int code;

	protected String errorCode;

	protected String errorMsg;

	public BasicRetVal() {
	}

	public BasicRetVal(int code) {
		this.code = code;
	}

	public BasicRetVal(int code, ErrorCode errorCode) {
		this.code = code;
		this.errorCode = errorCode.getValue();
		this.errorMsg = errorCode.getDesc();
	}

	public BasicRetVal(int code, String errorCode, String errorMsg) {
		this.code = code;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String toJSON() {
		return JSONUtils.toString(this);
	}
}
