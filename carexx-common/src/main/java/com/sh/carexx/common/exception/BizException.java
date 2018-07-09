package com.sh.carexx.common.exception;

import com.sh.carexx.common.ErrorCode;

/**
 * 
 * ClassName: BizException <br/> 
 * Function: 定义全局业务异常 <br/> 
 * Reason: 封装业务异常及业务错误 <br/> 
 * Date: 2018年4月24日 下午5:45:38 <br/> 
 * 
 * @author WL 
 * @since JDK 1.8
 */
public class BizException extends Exception {
	private static final long serialVersionUID = 1L;
	protected String code;
	protected String desc;

	public BizException() {
		super();
	}

	public BizException(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public BizException(ErrorCode errorCode) {
		super();
		this.code = errorCode.getValue();
		this.desc = errorCode.getDesc();
	}

	public BizException(Throwable e) {
		super(e);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String code, String desc, Throwable e) {
		super(e);
		this.code = code;
		this.desc = desc;
	}

	public BizException(ErrorCode errorCode, Throwable e) {
		super(e);
		this.code = errorCode.getValue();
		this.desc = errorCode.getDesc();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
