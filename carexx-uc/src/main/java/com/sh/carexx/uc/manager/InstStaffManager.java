package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.staff.InstStaffFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.enums.staff.StaffStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.InstStaff;
import com.sh.carexx.model.uc.InstStaffWorkType;
import com.sh.carexx.uc.service.InstStaffService;
import com.sh.carexx.uc.service.InstStaffWorkTypeService;

/**
 * 
 * ClassName: InstStaffManager <br/> 
 * Function: 人员管理 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午1:46:51 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class InstStaffManager {

	@Autowired
	public InstStaffService instStaffService;

	@Autowired
	public InstStaffWorkTypeService instStaffWorkTypeService;

	/**
	 * 
	 * add:(人员添加). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instStaffFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(InstStaffFormBean instStaffFormBean) throws BizException {
		//员工信息身份证校验
		if (instStaffService.getByIdNo(instStaffFormBean.getIdNo(), instStaffFormBean.getInstId()) != null) {
			throw new BizException(ErrorCode.INST_STAFF_EXISTS_ERROR);
		}
		
		InstStaff instStaff = new InstStaff();
		instStaff.setInstId(instStaffFormBean.getInstId());
		instStaff.setServiceInstId(instStaffFormBean.getServiceInstId());
		instStaff.setPersonType(instStaffFormBean.getPersonType());
		instStaff.setJobStatus(instStaffFormBean.getJobStatus());
		instStaff.setStaffStatus(StaffStatus.NORMAL.getValue());
		instStaff.setRealName(instStaffFormBean.getRealName());
		instStaff.setIdNo(instStaffFormBean.getIdNo());
		instStaff.setSex(instStaffFormBean.getSex());
		instStaff.setPhoto(instStaffFormBean.getPhoto());
		if (ValidUtils.isDate(instStaffFormBean.getBirthday())) {
			instStaff.setBirthday(DateUtils.toDate(instStaffFormBean.getBirthday(), DateUtils.YYYY_MM_DD));
		}
		instStaff.setPhone(instStaffFormBean.getPhone());
		instStaff.setAddress(instStaffFormBean.getAddress());
		if (ValidUtils.isDate(instStaffFormBean.getEntryDate())) {
			instStaff.setEntryDate(DateUtils.toDate(instStaffFormBean.getEntryDate(), DateUtils.YYYY_MM_DD));
		}
		if (ValidUtils.isDate(instStaffFormBean.getLeaveDate())) {
			instStaff.setLeaveDate(DateUtils.toDate(instStaffFormBean.getLeaveDate(), DateUtils.YYYY_MM_DD));
		}
		this.instStaffService.save(instStaff);

		Integer staffId = instStaff.getId();
		InstStaffWorkType instStaffWorkType = new InstStaffWorkType();
		instStaffWorkType.setStaffId(staffId);
		instStaffWorkType.setWorkTypeId(instStaffFormBean.getWorkTypeId());
		instStaffWorkType.setSettleStatus(UseStatus.DISABLED.getValue());
		instStaffWorkTypeService.save(instStaffWorkType);
	}

	/**
	 * 
	 * modify:(员工信息修改). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instStaffFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void modify(InstStaffFormBean instStaffFormBean) throws BizException {
		//员工信息身份证校验
		InstStaff oldinstStaff = instStaffService.getByIdNo(instStaffFormBean.getIdNo(), instStaffFormBean.getInstId());
		if (oldinstStaff != null && oldinstStaff.getId() != instStaffFormBean.getId()) {
			throw new BizException(ErrorCode.INST_STAFF_EXISTS_ERROR);
		}
		
		InstStaff instStaff = new InstStaff();
		instStaff.setId(instStaffFormBean.getId());
		instStaff.setServiceInstId(instStaffFormBean.getServiceInstId());
		instStaff.setPersonType(instStaffFormBean.getPersonType());
		instStaff.setJobStatus(instStaffFormBean.getJobStatus());
		instStaff.setRealName(instStaffFormBean.getRealName());
		instStaff.setIdNo(instStaffFormBean.getIdNo());
		instStaff.setSex(instStaffFormBean.getSex());
		instStaff.setPhoto(instStaffFormBean.getPhoto());
		if (ValidUtils.isDate(instStaffFormBean.getBirthday())) {
			instStaff.setBirthday(DateUtils.toDate(instStaffFormBean.getBirthday(), DateUtils.YYYY_MM_DD));
		}
		instStaff.setPhone(instStaffFormBean.getPhone());
		instStaff.setAddress(instStaffFormBean.getAddress());
		if (ValidUtils.isDate(instStaffFormBean.getEntryDate())) {
			instStaff.setEntryDate(DateUtils.toDate(instStaffFormBean.getEntryDate(), DateUtils.YYYY_MM_DD));
		}
		if (ValidUtils.isDate(instStaffFormBean.getLeaveDate())) {
			instStaff.setLeaveDate(DateUtils.toDate(instStaffFormBean.getLeaveDate(), DateUtils.YYYY_MM_DD));
		}
		this.instStaffService.update(instStaff);
	}

}
