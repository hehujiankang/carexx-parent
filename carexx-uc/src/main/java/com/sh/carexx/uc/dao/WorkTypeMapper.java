package com.sh.carexx.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.worktype.WorkTypeFormBean;
import com.sh.carexx.model.uc.WorkType;

/**
 * 
 * ClassName: WorkTypeMapper <br/>
 * Function: 工种信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface WorkTypeMapper {

	/**
	 * 
	 * insert:(增加工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workType
	 * @return
	 * @since JDK 1.8
	 */
	int insert(WorkType workType);

	/**
	 * 
	 * update:(修改工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workType
	 * @return
	 * @since JDK 1.8
	 */
	int update(WorkType workType);

	/**
	 * 
	 * updateStatus:(修改工种信息状态). <br/>
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
	 * selectAll:(查询所有工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<WorkType> selectAllAvailable();

	/**
	 * 
	 * selectWorkTypeCount:(查询分页工种信息总数). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectWorkTypeCount();

	/**
	 * 
	 * selectWorkTypeList:(按条件查询分页). <br/>
	 * 
	 * @author hetao
	 * @param workTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<WorkType> selectWorkTypeList(WorkTypeFormBean workTypeFormBean);

	/**
	 * 
	 * selectByWorkTypeName:(添加时查询工种是否已存在). <br/> 
	 * 
	 * @author hetao 
	 * @param WorkTypeName
	 * @return 
	 * @since JDK 1.8
	 */
	WorkType selectByWorkTypeName(@Param("workTypeName") String workTypeName);
}