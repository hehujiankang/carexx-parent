package com.sh.carexx.uc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.order.InstSettleQueryFormBean;
import com.sh.carexx.model.uc.InstSettle;

/**
 * 
 * ClassName: InstSettleMapper <br/>
 * Function: 关账 <br/>
 * Date: 2018年5月25日 上午11:48:43 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstSettleMapper {
	/**
	 * 
	 * selectById:(通过id查询关账记录). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstSettle selectById(Long id);

	/**
	 * 
	 * selectBySettleDate:(传入一个时间查询最近时间). <br/>
	 * 
	 * @author hetao
	 * @param settleDate
	 * @return
	 * @since JDK 1.8
	 */
	Date selectBySettleDate(@Param("settleDate") Date settleDate, @Param("instId") Integer instId);

	/**
	 * 
	 * selectAllBySettleStatus:(查询所有状态为关账的记录). <br/>
	 * 
	 * @author zhoulei
	 * @param instId
	 * @return
	 * @since JDK 1.8
	 */
	List<InstSettle> selectAllBySettleStatus(@Param("settleDate") Date settleDate, @Param("instId") Integer instId);

	/**
	 * 
	 * selectMaxSettleDateBySettleStatus:(查询最近关账时间). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Date selectMaxSettleDateBySettleStatus(@Param("instId") Integer instId);

	/**
	 * 
	 * selectInstSettleCount:(关账记录分页统计). <br/>
	 * 
	 * @author hetao
	 * @param instSettleQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstSettleCount(InstSettleQueryFormBean instSettleQueryFormBean);

	/**
	 * 
	 * selectInstSettleList:(关账记录分页查询). <br/>
	 * 
	 * @author hetao
	 * @param instSettleQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstSettleList(InstSettleQueryFormBean instSettleQueryFormBean);

	/**
	 * 
	 * insert:(添加关账方法). <br/>
	 * 
	 * @author hetao
	 * @param instSettle
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstSettle instSettle);

	/**
	 * 
	 * updateStatus:(修改账单状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param modifier
	 * @param srcStatus
	 * @param targetStatus
	 * @return
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("id") Long id, @Param("modifier") String modifier, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);
}