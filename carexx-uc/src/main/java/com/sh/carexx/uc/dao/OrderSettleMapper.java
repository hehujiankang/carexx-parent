package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.model.uc.OrderSettle;

/**
 * 
 * ClassName: OrderSettleMapper <br/> 
 * Function: 客户服务订单结算 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年5月15日 下午2:40:35 <br/> 
 * 
 * @author pc 
 * @since JDK 1.8
 */
public interface OrderSettleMapper {
	/**
	 * 
	 * selectById:(通过id查询订单结算信息). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	OrderSettle selectById(Long id);
	
	/**
	 * 
	 * selectByScheduleId:(通过排班id查询订单结算信息). <br/> 
	 * 
	 * @author zhoulei 
	 * @param scheduleId
	 * @return 
	 * @since JDK 1.8
	 */
	OrderSettle selectByScheduleId(Long scheduleId);

	/**
	 * 
	 * insert:(添加订单结算信息方法). <br/>
	 * 
	 * @author zhoulei
	 * @param orderSettle
	 * @return
	 * @since JDK 1.8
	 */
	int insert(OrderSettle orderSettle);
	
	/**
	 * 
	 * updateBatch:(订单结算批量关闭方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param scheduleList
	 * @return 
	 * @since JDK 1.8
	 */
	int batchCloseUpdate(List<CustomerOrderSchedule> scheduleList);
	
	/**
	 * 
	 * updateBatchReduction:(订单结算批量恢复方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param scheduleList
	 * @return 
	 * @since JDK 1.8
	 */
	int batchOpenUpdate(List<CustomerOrderSchedule> scheduleList);
	/**
	 * 
	 * updateStatus:(修改状态). <br/> 
	 * 
	 * @author zhoulei 
	 * @param scheduleId
	 * @param srcStatus
	 * @param targetStatus
	 * @return 
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("scheduleId") Long scheduleId, @Param("srcStatus") Byte srcStatus, @Param("targetStatus") Byte targetStatus);
	
	/**
	 * 
	 * selectOrderSettleCount:(结算统计报表). <br/> 
	 * 
	 * @author hetao 
	 * @param workQuantityReportFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?,?>> selectOrderSettleCount(WorkQuantityReportFormBean workQuantityReportFormBean);
	
	/**
	 * 
	 * updateSettleAmt:(调整结算款). <br/> 
	 * 
	 * @author zhoulei 
	 * @param orderSettle
	 * @return 
	 * @since JDK 1.8
	 */
	int updateSettleAmt(OrderSettle orderSettle);
}