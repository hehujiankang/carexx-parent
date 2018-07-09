package com.sh.carexx.uc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.worktype.WorkTypeSettleQueryFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstWorkTypeSettle;

/**
 * 
 * ClassName: InstWorkTypeSettleService <br/>
 * Function: 工种结算信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstWorkTypeSettleService {

	/**
	 * 
	 * getById:(通过id查找). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstWorkTypeSettle getById(Integer id);

	/**
	 * 
	 * queryAllAvailable:(查询所有启用结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param workTypeId
	 * @return
	 * @since JDK 1.8
	 */
	List<InstWorkTypeSettle> queryAllAvailable(Integer instId, Integer workTypeId);

	/**
	 * 
	 * getInstWorkTypeSettleCount:(查询符合分页条件的总数). <br/>
	 * 
	 * @author hetao
	 * @param selectInstSettleFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getInstWorkTypeSettleCount(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean);

	/**
	 * 
	 * queryInstWorkTypeSettleList:(分页连接查询机构工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param selectInstSettleFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstWorkTypeSettleList(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean);

	/**
	 * 
	 * save:(添加工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instWorkTypeSettle
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(InstWorkTypeSettle instWorkTypeSettle) throws BizException;

	/**
	 * 
	 * update:(修改工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instWorkTypeSettle
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(InstWorkTypeSettle instWorkTypeSettle) throws BizException;

	/**
	 * 
	 * updateStatus:(修改工种结算信息状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException;

	/**
	 * 
	 * queryByExist:(查询结算信息是否存在). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param workTypeId
	 * @param settleRatio
	 * @return
	 * @since JDK 1.8
	 */
	InstWorkTypeSettle queryByExistence(Integer instId, Integer workTypeId, BigDecimal settleRatio);
}
