package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.staff.InstStaffQueryFormBean;
import com.sh.carexx.model.uc.InstStaff;

/**
 * 
 * ClassName: InstStaffMapper <br/>
 * Function: 人员管理 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月2日 上午11:40:35 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface InstStaffMapper {

	/**
	 * 
	 * insert:(添加员工方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaff
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstStaff instStaff);

	/**
	 * 
	 * selectById:(通过id查询员工记录). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstStaff selectById(Integer id);

	/**
	 * 
	 * SelectByIdNo:(通过身份证号码查询员工记录). <br/>
	 * 
	 * @author zhoulei
	 * @param idNo
	 * @return
	 * @since JDK 1.8
	 */
	InstStaff selectByIdNo(@Param("idNo") String idNo, @Param("instId") Integer instId);

	/**
	 * 
	 * selectInstStaffCountByServiceId:(通过服务id统计会该项技能的员工). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstStaffCountByServiceId(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * selectInstStaffListByServiceId:(通过服务id查询会该项技能的员工). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstStaffListByServiceId(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * selectInstStaffCount:(员工信息分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstStaffCount(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * selectInstStaffList:(员工信息分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstStaffList(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * update:(修改员工方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaff
	 * @return
	 * @since JDK 1.8
	 */
	int update(InstStaff instStaff);

	/**
	 * 
	 * delete:(删除员工（状态改为2）方法). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	int delete(Integer id);
}