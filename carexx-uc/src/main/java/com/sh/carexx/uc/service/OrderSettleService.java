package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.OrderSettle;

/**
 * 
 * ClassName: OrderSettleService <br/>
 * Function: 客户服务订单结算 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月4日 下午5:04:45 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface OrderSettleService {

	OrderSettle getByScheduleId(Long scheduleId);
	/**
	 * 
	 * save:(添加客户服务订单结算). <br/>
	 * 
	 * @author zhoulei
	 * @param orderSettle
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(OrderSettle orderSettle) throws BizException;
	
	/**
	 * 
	 * billShutDown:(订单结算批量关闭方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param scheduleList
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void batchCloseUpdate(List<CustomerOrderSchedule> scheduleList) throws BizException;
	
	/**
	 * 
	 * billReduction:(订单结算批量恢复方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param scheduleList
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void batchOpenUpdate(List<CustomerOrderSchedule> scheduleList) throws BizException;
	/**
	 * 
	 * updateStatus:(修改状态). <br/> 
	 * 
	 * @author hetao 
	 * @param scheduleId
	 * @param srcStatus
	 * @param targetStatus
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void updateStatus(Long scheduleId, Byte srcStatus, Byte targetStatus) throws BizException;
	
	/**
	 * 
	 * updateSettleAmt:(调整结算款). <br/> 
	 * 
	 * @author zhoulei 
	 * @param orderSettle
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void updateSettleAmt(OrderSettle orderSettle) throws BizException;
	
	/**
	 * 
	 * queryOrderSettleCount:(结算统计). <br/> 
	 * 
	 * @author hetao 
	 * @param workQuantityReportFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?,?>> queryOrderSettleCount(WorkQuantityReportFormBean workQuantityReportFormBean);
}
