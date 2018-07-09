package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstStaffWorkType;

/**
 * 
 * ClassName: InstStaffWorkTypeService <br/>
 * Function: 机构员工工种结算 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月3日 上午10:36:28 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface InstStaffWorkTypeService {

	/**
	 * 
	 * save:(添加员工工种方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffWorkType
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(InstStaffWorkType instStaffWorkType) throws BizException;

	/**
	 * 
	 * getById:(通过id查询员工工种信息). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	InstStaffWorkType getById(Long id);

	/**
	 * 
	 * getInstStaffWorkTypeCount:(员工工种分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param InstStaffWorkTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getInstStaffWorkTypeCount(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean);

	/**
	 * 
	 * queryInstStaffWorkTypeList:(员工工种分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param InstStaffWorkTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstStaffWorkTypeList(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean);

	/**
	 * 
	 * getByStaffId:(通过员工id查询员工工种). <br/>
	 * 
	 * @author zhoulei
	 * @param staffId
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> getByStaffId(Integer staffId);

	/**
	 * 
	 * getByStaffIdAndWorkTypeId:(添加校验). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffWorkTypeFormBean
	 * @return
	 * @since JDK 1.8
	 */
	InstStaffWorkType getByStaffIdAndWorkTypeId(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean);

	/**
	 * 
	 * update:(修改员工工种信息方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instStaffWorkType
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(InstStaffWorkType instStaffWorkType) throws BizException;

	/**
	 * 
	 * updateStatus:(修改员工工种信息状态). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void updateStatus(Long id, Byte srcStatus, Byte targetStatus) throws BizException;
}
