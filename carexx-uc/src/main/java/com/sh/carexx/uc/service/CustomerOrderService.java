package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.order.CustomerOrderQueryFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrder;

/**
 * 
 * ClassName: 客户订单 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface CustomerOrderService {

	/**
	 * 
	 * save:(添加方法). <br/>
	 * 
	 * @author hetao
	 * @param customerOrder
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(CustomerOrder customerOrder) throws BizException;
	
	/**
	 * 
	 * confirmCompleted:(完成订单). <br/> 
	 * 
	 * @author hetao 
	 * @param customerOrder 
	 * @since JDK 1.8
	 */
	void confirmCompleted(CustomerOrder customerOrder) throws BizException;

	/**
	 * 
	 * getByUserIdCount:(客户端通过客户id查询服务订单分页总数). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getByUserIdCount(CustomerOrderQueryFormBean customerOrderQueryFormBean);

	/**
	 * 
	 * getByUserId:(客户端通过客户id查询服务订单分页). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> getByUserId(CustomerOrderQueryFormBean customerOrderQueryFormBean);

	/**
	 * 
	 * getCustomerOrderCount:(查询客户预约服务订单总数). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getCustomerOrderCount(CustomerOrderQueryFormBean customerOrderQueryFormBean);

	/**
	 * 
	 * queryCustomerOrderList:(查询客户预约服务订单分页). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryCustomerOrderList(CustomerOrderQueryFormBean customerOrderQueryFormBean);

	/**
	 * 
	 * getByOrderNo:(通过订单号查询). <br/>
	 * 
	 * @author zhoulei
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	CustomerOrder getByOrderNo(String orderNo);
	
	/**
	 * 
	 * queryOrderByExist:(查询客户下单重复). <br/> 
	 * 
	 * @author hetao 
	 * @param customerOrderQueryFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?,?>> queryOrderExistence(CustomerOrderQueryFormBean customerOrderQueryFormBean);

	/**
	 * 
	 * updateStatus:(修改订单状态). <br/>
	 * 
	 * @author hetao
	 * @param orderStatus
	 * @param orderNo
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void updateStatus(String orderNo, Byte srcStatus, Byte targetStatus) throws BizException;
	
	/**
	 * 
	 * updateOrderCancel:(取消订单). <br/> 
	 * 
	 * @author hetao 
	 * @param orderNo
	 * @param targetStatus
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void updateOrderCancel(String orderNo, Byte targetStatus) throws BizException;
	
	/**
	 * 
	 * queryIncomeCount:(收入统计). <br/> 
	 * 
	 * @author zhoulei 
	 * @param customerOrderQueryFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?,?>> queryIncomeCount(CustomerOrderQueryFormBean customerOrderQueryFormBean);
	
	/**
	 * 
	 * updateAdjustAmt:(调整订单金额). <br/> 
	 * 
	 * @author hetao 
	 * @param customerOrder
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void update(CustomerOrder customerOrder) throws BizException;
}
