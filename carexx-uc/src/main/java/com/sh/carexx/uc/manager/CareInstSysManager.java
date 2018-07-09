package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.CareInstSysFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInstSys;
import com.sh.carexx.uc.service.CareInstSysService;

@Service
public class CareInstSysManager {

	@Autowired
	public CareInstSysService careInstSysService;

	public void add(CareInstSysFormBean careInstSysFormBean) throws BizException {
		if (careInstSysService.getByInstSysName(careInstSysFormBean) != null) {
			throw new BizException(ErrorCode.INST_SYS_EXISTS_ERROR);
		} else {
			CareInstSys careInstSys = new CareInstSys();
			careInstSys.setInstId(careInstSysFormBean.getInstId());
			careInstSys.setInstSysName(careInstSysFormBean.getInstSysName());
			careInstSys.setInstSysStatus(UseStatus.ENABLED.getValue());
			careInstSys.setIntroduce(careInstSysFormBean.getIntroduce());

			this.careInstSysService.save(careInstSys);
		}
	}

	public void enable(Integer id) throws BizException {
		this.careInstSysService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	public void disable(Integer id) throws BizException {
		this.careInstSysService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
