package com.sh.carexx.common.web;

/**
 * 
 * ClassName: DataRetVal <br/> 
 * Function: 接口基本输出模型（带业务数据） <br/> 
 * Reason: 定义输出模型 <br/> 
 * Date: 2018年4月24日 下午5:46:42 <br/> 
 * 
 * @author WL 
 * @since JDK 1.8
 */
public class DataRetVal extends BasicRetVal {

	private Object data;

	public DataRetVal() {
		super();
	}

	public DataRetVal(int code, Object data) {
		super(code);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
