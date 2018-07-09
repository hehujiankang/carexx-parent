package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.model.uc.InstStaffWorkType;

/**
 * 
 * ClassName: InstStaffWorkTypeMapper <br/>
 * Function: 机构员工工种 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月3日 上午10:33:22 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface InstStaffWorkTypeMapper {

	/**
	 * 
	 * selectById:(通过id查询员工工种信息). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstStaffWorkType selectById(Long id);

	/**
	 * 
	 * insert:(添加员工工种方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffWorkType
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstStaffWorkType instStaffWorkType);

	/**
	 * 
	 * selectInstStaffWorkTypeCount:(员工工种分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffWorkTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstStaffWorkTypeCount(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean);

	/**
	 * 
	 * selectInstStaffWorkTypeList:(员工工种分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffWorkTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstStaffWorkTypeList(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean);

	/**
	 * 
	 * selectByStaffId:(通过员工id查询员工工种). <br/> 
	 * 
	 * @author zhoulei 
	 * @param staffId
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectByStaffId(@Param("staffId") Integer staffId);

	/**
	 * 
	 * selectByStaffIdAndWorkTypeId:(添加校验). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instStaffWorkTypeFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	InstStaffWorkType selectByStaffIdAndWorkTypeId(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean);
	
	/**
	 * 
	 * update:(修改员工工种方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instStaffWorkType
	 * @return 
	 * @since JDK 1.8
	 */
	int update(InstStaffWorkType instStaffWorkType);

	/**
	 * 
	 * updateStatus:(修改员工工种信息状态). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @return
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("id") Long id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);
}