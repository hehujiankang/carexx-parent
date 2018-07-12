package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstStaffWorkType;
import com.sh.carexx.uc.service.InstStaffWorkTypeService;

/**
 * 
 * ClassName: InstStaffWorkTypeManager <br/> 
 * Function: 人员工种管理 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午1:48:45 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class InstStaffWorkTypeManager {

	@Autowired
	public InstStaffWorkTypeService instStaffWorkTypeService;

	/**
	 * 
	 * add:(人员工种添加). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instStaffWorkTypeFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) throws BizException {
		//人员工种校验是否工种已存在
		if (instStaffWorkTypeService.getByStaffIdAndWorkTypeId(instStaffWorkTypeFormBean) != null) {
			throw new BizException(ErrorCode.STAFF_WORK_TYPE_EXISTS_ERROR);
		}
		
		InstStaffWorkType instStaffWorkType = new InstStaffWorkType();
		instStaffWorkType.setStaffId(instStaffWorkTypeFormBean.getStaffId());
		instStaffWorkType.setWorkTypeId(instStaffWorkTypeFormBean.getWorkTypeId());
		instStaffWorkType.setSettleStatus(UseStatus.DISABLED.getValue());

		instStaffWorkTypeService.save(instStaffWorkType);
	}

	/**
	 * 
	 * modify:(人员修改). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instStaffWorkTypeFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void modify(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) throws BizException {
		//人员工种校验是否工种已存在
		InstStaffWorkType oldinstStaffWorkType = instStaffWorkTypeService
				.getByStaffIdAndWorkTypeId(instStaffWorkTypeFormBean);
		if (oldinstStaffWorkType != null && oldinstStaffWorkType.getId() != instStaffWorkTypeFormBean.getId()) {
			throw new BizException(ErrorCode.STAFF_WORK_TYPE_EXISTS_ERROR);
		}
		
		InstStaffWorkType instStaffWorkType = new InstStaffWorkType();
		instStaffWorkType.setId(instStaffWorkTypeFormBean.getId());
		instStaffWorkType.setStaffId(instStaffWorkTypeFormBean.getStaffId());
		instStaffWorkType.setWorkTypeId(instStaffWorkTypeFormBean.getWorkTypeId());
		this.instStaffWorkTypeService.update(instStaffWorkType);
	}

	public void enable(Long id) throws BizException {
		this.instStaffWorkTypeService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	public void disable(Long id) throws BizException {
		this.instStaffWorkTypeService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
