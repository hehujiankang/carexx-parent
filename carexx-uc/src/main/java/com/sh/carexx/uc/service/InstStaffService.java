package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.staff.InstStaffQueryFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstStaff;

/**
 * 
 * ClassName: InstStaffService <br/>
 * Function: 人员管理 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月2日 下午12:32:13 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface InstStaffService {
	/**
	 * 
	 * save:(添加员工方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaff
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(InstStaff instStaff) throws BizException;

	/**
	 * 
	 * getById:(通过id查询员工记录). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstStaff getById(Integer id);

	/**
	 * 
	 * getByIdNo:(通过身份证号码查询员工记录). <br/>
	 * 
	 * @author zhoulei
	 * @param idNo
	 * @return
	 * @since JDK 1.8
	 */
	InstStaff getByIdNo(String idNo, Integer instId);

	/**
	 * 
	 * getByServiceIdCount:(通过服务id统计会该项技能的员工). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getInstStaffCountByServiceId(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * queryInstStaffListByServiceId:(通过服务id查询会该项技能的员工). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffQueryFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstStaffListByServiceId(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * getInstStaffCount:(员工信息分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getInstStaffCount(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * queryInstStaffList:(员工信息分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstStaffList(InstStaffQueryFormBean instStaffQueryFormBean);

	/**
	 * 
	 * update:(修改员工方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaff
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(InstStaff instStaff) throws BizException;

	/**
	 * 
	 * delete:(删除员工（状态改为2）方法). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void delete(Integer id) throws BizException;
}
