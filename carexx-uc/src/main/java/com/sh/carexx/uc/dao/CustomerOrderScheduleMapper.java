package com.sh.carexx.uc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.model.uc.CustomerOrderSchedule;

/**
 * 
 * ClassName: CustomerOrderScheduleMapper <br/> 
 * Function: 订单排班 <br/> 
 * Date: 2018年5月25日 上午11:49:18 <br/> 
 * 
 * @author hetao 
 * @since JDK 1.8
 */
public interface CustomerOrderScheduleMapper {

	/**
	 * 
	 * insert:(添加排班). <br/>
	 * 
	 * @author hetao
	 * @param customerOrderSchedule
	 * @return
	 * @since JDK 1.8
	 */
	int insert(CustomerOrderSchedule customerOrderSchedule);

	/**
	 * 
	 * selectCustomerOrderSchedule:(查询所有排班). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectCustomerOrderSchedule();
	
	/**
	 * 
	 * selectById:(通过主键查询). <br/> 
	 * 
	 * @author hetao 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	CustomerOrderSchedule selectById(Long id);

	/**
	 * 
	 * selectByOrderNo:(通过订单编号查询). <br/>
	 * 
	 * @author zhoulei
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	List<CustomerOrderSchedule> selectByOrderNo(String orderNo);

	/**
	 * 
	 * selectOrderScheduleStaff:(通过订单号查排班). <br/>
	 * 
	 * @author hetao
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectScheduleByOrderNo(String orderNo);

	/**
	 * 
	 * selectByExist:(添加排班时查询是否已排班). <br/>
	 * 
	 * @author hetao
	 * @param orderNo
	 * @param serviceStartTime
	 * @param serviceEndTime
	 * @return
	 * @since JDK 1.8
	 */
	List<CustomerOrderSchedule> selectByExistence(@Param("orderNo") String orderNo,
			@Param("serviceStartTime") Date serviceStartTime, @Param("serviceEndTime") Date serviceEndTime);

	/**
	 * 
	 * updateStatus:(修改排班状态). <br/>
	 * 
	 * @author hetao
	 * @param serviceStatus
	 * @param orderNo
	 * @return
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("id") Long id, @Param("srcStatus") Byte srcStatus, @Param("targetStatus") Byte targetStatus);
	
	/**
	 * 
	 * updateOrderScheduleCancel:(取消订单时同时删除排班). <br/> 
	 * 
	 * @author hetao 
	 * @param orderNo
	 * @param targetStatus
	 * @return 
	 * @since JDK 1.8
	 */
	int deleteOrderSchedule(@Param("id") Long id, @Param("targetStatus") Byte targetStatus);
	
	/**
	 * 
	 * selectByTimeBefore:(通过时间查询排班). <br/> 
	 * 
	 * @author hetao 
	 * @param month
	 * @return 
	 * @since JDK 1.8
	 */
	List<CustomerOrderSchedule> selectByTime(@Param("recentlySettleDate") Date recentlySettleDate, @Param("settleDate") Date settleDate,@Param("srcStatus") Byte srcStatus,@Param("instId")Integer instId);
	
	/**
	 * 
	 * selectWorkQuantityReport:(工量结算报表). <br/> 
	 * 
	 * @author hetao 
	 * @param workQuantityReportFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?,?>> selectWorkQuantityReport(WorkQuantityReportFormBean workQuantityReportFormBean);
}