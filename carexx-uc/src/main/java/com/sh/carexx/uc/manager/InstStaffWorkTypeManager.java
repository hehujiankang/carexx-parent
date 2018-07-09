package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstStaffWorkType;
import com.sh.carexx.uc.service.InstStaffWorkTypeService;

@Service
public class InstStaffWorkTypeManager {

	@Autowired
	public InstStaffWorkTypeService instStaffWorkTypeService;

	public void add(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) throws BizException {
		if (instStaffWorkTypeService.getByStaffIdAndWorkTypeId(instStaffWorkTypeFormBean) != null) {
			throw new BizException(ErrorCode.STAFF_WORK_TYPE_EXISTS_ERROR);
		}
		InstStaffWorkType instStaffWorkType = new InstStaffWorkType();
		instStaffWorkType.setStaffId(instStaffWorkTypeFormBean.getStaffId());
		instStaffWorkType.setWorkTypeId(instStaffWorkTypeFormBean.getWorkTypeId());
		instStaffWorkType.setSettleStatus(UseStatus.DISABLED.getValue());

		instStaffWorkTypeService.save(instStaffWorkType);
	}

	public void modify(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) throws BizException {
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
