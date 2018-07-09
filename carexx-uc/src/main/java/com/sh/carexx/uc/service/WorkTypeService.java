package com.sh.carexx.uc.service;

import java.util.List;

import com.sh.carexx.bean.worktype.WorkTypeFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.WorkType;

/**
 * 
 * ClassName: WorkTypeService <br/>
 * Function: 工种信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface WorkTypeService {

	/**
	 * 
	 * save:(增加工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workType
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(WorkType workType) throws BizException;

	/**
	 * 
	 * update:(修改工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workType
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(WorkType workType) throws BizException;

	/**
	 * 
	 * updateStats:(修改工种信息状态). <br/>
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
	 * queryAllAvailable:(查询所有可用工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<WorkType> queryAllAvailable();

	/**
	 * 
	 * getWorkTypeCount:(查询分页工种信息总数). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Integer getWorkTypeCount();

	/**
	 * 
	 * selectWorkTypeList:(按条件查询分页). <br/>
	 * 
	 * @author hetao
	 * @param workTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<WorkType> queryWorkTypeList(WorkTypeFormBean workTypeFormBean);

	/**
	 * 
	 * getByWorkTypeName:(添加时查询工种是否已存在). <br/>
	 * 
	 * @author hetao
	 * @param WorkTypeName
	 * @return
	 * @since JDK 1.8
	 */
	WorkType getByWorkTypeName(String workTypeName);
}
