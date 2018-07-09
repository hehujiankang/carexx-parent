package com.sh.carexx.uc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.order.InstSettleQueryFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstSettle;

/**
 * 
 * ClassName: InstSettleService <br/>
 * Function: 关账 <br/>
 * Date: 2018年5月25日 上午11:50:27 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstSettleService {
	/**
	 * 
	 * getById:(通过id查询关账信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstSettle getById(Long id);

	/**
	 * 
	 * getBySettleDate:(传入一个时间查询最近时间). <br/>
	 * 
	 * @author hetao
	 * @param settleDate
	 * @return
	 * @since JDK 1.8
	 */
	Date getBySettleDate(Date settleDate, Integer instId);

	/**
	 * 
	 * queryMaxSettleDateBySettleStatus:(查询最近关账时间). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Date queryMaxSettleDateBySettleStatus(Integer instId);

	/**
	 * 
	 * queryAllBySettleStatus:(查询所有状态为关账的记录). <br/>
	 * 
	 * @author zhoulei
	 * @param instId
	 * @return
	 * @since JDK 1.8
	 */
	List<InstSettle> queryAllBySettleStatus(Date settleDate, Integer instId);

	/**
	 * 
	 * getInstSettleCount:(关账信息分页统计). <br/>
	 * 
	 * @author hetao
	 * @param instSettleQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getInstSettleCount(InstSettleQueryFormBean instSettleQueryFormBean);

	/**
	 * 
	 * queryAllSettle:(关账信息查询分页). <br/>
	 * 
	 * @author zhoulei
	 * @param instId
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstSettleList(InstSettleQueryFormBean instSettleQueryFormBean);

	/**
	 * 
	 * save:(添加关账信息方法). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param settleMonth
	 * @param creator
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(InstSettle instSettle) throws BizException;

	/**
	 * 
	 * updateStatus:(修改关账信息状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void updateStatus(Long id, String modifier, Byte srcStatus, Byte targetStatus) throws BizException;
}
