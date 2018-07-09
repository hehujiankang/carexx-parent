package com.sh.carexx.uc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.worktype.WorkTypeSettleQueryFormBean;
import com.sh.carexx.model.uc.InstWorkTypeSettle;

/**
 * 
 * ClassName: InstWorkTypeSettleMapper <br/>
 * Function: 工种结算信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstWorkTypeSettleMapper {

	/**
	 * 
	 * selectById:(通过id查询方法). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstWorkTypeSettle selectById(Integer id);

	/**
	 * 
	 * selectAllAvailable:(查询所有启用结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param workTypeId
	 * @return
	 * @since JDK 1.8
	 */
	List<InstWorkTypeSettle> selectAllAvailable(@Param("instId") Integer instId,
			@Param("workTypeId") Integer workTypeId);

	/**
	 * 
	 * selectInstWorkTypeSettleCount:(查询符合分页条件的总数). <br/>
	 * 
	 * @author hetao
	 * @param selectInstSettleFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstWorkTypeSettleCount(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean);

	/**
	 * 
	 * selectInstWorkTypeSettleList:(分页连接查询机构工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param selectInstSettleFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstWorkTypeSettleList(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean);

	/**
	 * 
	 * insert:(添加工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instWorkTypeSettle
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstWorkTypeSettle instWorkTypeSettle);

	/**
	 * 
	 * update:(修改工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instWorkTypeSettle
	 * @return
	 * @since JDK 1.8
	 */
	int update(InstWorkTypeSettle instWorkTypeSettle);

	/**
	 * 
	 * updateStatus:(修改工种结算信息状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @return
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);

	/**
	 * 
	 * selectByExist:(查询结算信息是否存在). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param workTypeId
	 * @param settleRatio
	 * @return
	 * @since JDK 1.8
	 */
	InstWorkTypeSettle selectByExistence(@Param("instId") Integer instId, @Param("workTypeId") Integer workTypeId,
			@Param("settleRatio") BigDecimal settleRatio);
}