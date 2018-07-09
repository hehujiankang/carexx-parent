package com.sh.carexx.uc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerOrderSchedule;

/**
 * 
 * ClassName: CustomerOrderScheduleService <br/> 
 * Function: 订单排班 <br/> 
 * Date: 2018年5月25日 上午11:50:04 <br/> 
 * 
 * @author hetao 
 * @since JDK 1.8
 */
public interface CustomerOrderScheduleService {
	/**
	 * 
	 * save:(添加排班). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderSchedule
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(CustomerOrderSchedule customerOrderSchedule) throws BizException;

	/**
	 * 
	 * queryCustomerOrderSchedule:(查询所有排班). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryCustomerOrderSchedule();

	/**
	 * 
	 * getById:(通过id查询). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CustomerOrderSchedule getById(Long id);

	/**
	 * 
	 * getByOrderNo:(通过订单号查询). <br/>
	 * 
	 * @author zhoulei
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	List<CustomerOrderSchedule> getByOrderNo(String orderNo);

	/**
	 * 
	 * queryOrderScheduleStaff:(通过订单号查询排班). <br/>
	 * 
	 * @author hetao
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryScheduleByOrderNo(String orderNo);

	/**
	 * 
	 * queryByExist:(添加排班时查询是否已排班). <br/>
	 * 
	 * @author hetao
	 * @param orderNo
	 * @param serviceStartTime
	 * @param serviceEndTime
	 * @return
	 * @since JDK 1.8
	 */
	List<CustomerOrderSchedule> queryByExistence(String orderNo, Date serviceStartTime, Date serviceEndTime);

	/**
	 * 
	 * updateStatus:(修改订单状态). <br/>
	 * 
	 * @author hetao
	 * @param serviceStatus
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	void updateStatus(Long id, Byte srcStatus, Byte targetStatus) throws BizException;

	/**
	 * 
	 * deleteOrderSchedule:(取消订单时同时删除排班). <br/>
	 * 
	 * @author hetao
	 * @param orderNo
	 * @param targetStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void deleteOrderSchedule(Long id, Byte targetStatus) throws BizException;

	/**
	 * 
	 * getByTimeBefore:(通过时间查询排班). <br/>
	 * 
	 * @author hetao
	 * @param month
	 * @return
	 * @since JDK 1.8
	 */
	List<CustomerOrderSchedule> getByTime(Date recentlySettleDate, Date settleDate, Byte srcStatus,Integer instId);

	/**
	 * 
	 * queryWorkQuantityReport:(工量结算报表). <br/>
	 * 
	 * @author hetao
	 * @param workQuantityReportFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> queryWorkQuantityReport(WorkQuantityReportFormBean workQuantityReportFormBean);
}
