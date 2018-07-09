package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.OrderPayment;

/**
 * 
 * ClassName: OrderPaymentService <br/>
 * Function: 订单支付信息. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月4日 下午1:39:25 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface OrderPaymentService {

	/**
	 * 
	 * save:(添加订单支付信息方法). <br/>
	 * 
	 * @author zhoulei
	 * @param orderPayment
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(OrderPayment orderPayment) throws BizException;

	/**
	 * 
	 * getById:(通过id查询订单). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	OrderPayment getById(Long id);
	
	/**
	 * 
	 * getByOrderNo:(通过订单编号查询订单). <br/> 
	 * 
	 * @author zhoulei 
	 * @param orderNo
	 * @return 
	 * @since JDK 1.8
	 */
	OrderPayment getByOrderNo(String orderNo);

	/**
	 * 
	 * update:(修改订单支付信息方法). <br/>
	 * 
	 * @author zhoulei
	 * @param orderPayment
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(OrderPayment orderPayment) throws BizException;
}
