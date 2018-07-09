package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.model.uc.InstInpatientArea;

public interface InstInpatientAreaMapper {
	
	/**
	 * 
	 * selectById:(通过id查询). <br/>  
	 * 
	 * @author zhoulei 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	InstInpatientArea selectById(Integer id);

	/**
	 * 
	 * selectByInpatientArea:(通过病区查询). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	InstInpatientArea selectByInpatientArea(InstInpatientAreaFormBean instInpatientAreaFormBean);

	/**
	 * 
	 * insert:(添加病区). <br/>  
	 * 
	 * @author zhoulei 
	 * @param instInpatientArea
	 * @return 
	 * @since JDK 1.8
	 */
	int insert(InstInpatientArea instInpatientArea);

	/**
	 * 
	 * selectInstInpatientAreaCount:(病区统计查询). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	Integer selectInstInpatientAreaCount(InstInpatientAreaFormBean instInpatientAreaFormBean);

	/**
	 * 
	 * selectInstInpatientAreaList:(病区分页查询). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstInpatientAreaList(InstInpatientAreaFormBean instInpatientAreaFormBean);

	/**
	 * 
	 * selectAllInstInpatientArea:(查询所有病区). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instId
	 * @return 
	 * @since JDK 1.8
	 */
	List<InstInpatientArea> selectAllInstInpatientArea(@Param("instId") Integer instId);

	/**
	 * 
	 * update:(修改病区). <br/> 

	 * 
	 * @author zhoulei 
	 * @param instInpatientArea
	 * @return 
	 * @since JDK 1.8
	 */
	int update(InstInpatientArea instInpatientArea);

	/**
	 * 
	 * delete:(删除病区). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	int delete(Integer id);
}