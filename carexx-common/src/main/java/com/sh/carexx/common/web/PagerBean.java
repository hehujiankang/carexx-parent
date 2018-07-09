package com.sh.carexx.common.web;

import java.util.List;

/**
 * 
 * ClassName: PagerBean <br/> 
 * Function: 分页数据模型 <br/> 
 * Reason: 包装分页数据 <br/> 
 * Date: 2018年4月24日 下午5:46:42 <br/> 
 * 
 * @author WL 
 * @since JDK 1.8
 */
public class PagerBean {
	private int totalNum;
	private List<?> items;

	public PagerBean(int totalNum, List<?> items) {
		super();
		this.totalNum = totalNum;
		this.items = items;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

}
