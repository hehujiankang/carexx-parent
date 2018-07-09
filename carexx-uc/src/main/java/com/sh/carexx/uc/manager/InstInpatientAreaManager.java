package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstInpatientArea;
import com.sh.carexx.uc.service.InstInpatientAreaService;

@Service
public class InstInpatientAreaManager {

	@Autowired
	private InstInpatientAreaService instInpatientAreaService;

	public void add(InstInpatientAreaFormBean instInpatientAreaFormBean) throws BizException {
		if (instInpatientAreaService.getByInpatientArea(instInpatientAreaFormBean) != null) {
			throw new BizException(ErrorCode.INST_INPATIENT_AREA_EXISTS_ERROR);
		}
		InstInpatientArea instInpatientArea = new InstInpatientArea();
		instInpatientArea.setInstId(instInpatientAreaFormBean.getInstId());
		instInpatientArea.setInpatientArea(instInpatientAreaFormBean.getInpatientArea());
		instInpatientArea.setAreaStatus(UseStatus.ENABLED.getValue());
		instInpatientArea.setIntroduce(instInpatientAreaFormBean.getIntroduce());

		this.instInpatientAreaService.save(instInpatientArea);
	}

	public void modify(InstInpatientAreaFormBean instInpatientAreaFormBean) throws BizException {
		InstInpatientArea oldInstInpatientArea = instInpatientAreaService.getByInpatientArea(instInpatientAreaFormBean);
		if (oldInstInpatientArea != null && oldInstInpatientArea.getId() != instInpatientAreaFormBean.getId()) {
			throw new BizException(ErrorCode.INST_INPATIENT_AREA_EXISTS_ERROR);
		}
		InstInpatientArea instInpatientArea = new InstInpatientArea();
		instInpatientArea.setId(instInpatientAreaFormBean.getId());
		instInpatientArea.setInstId(instInpatientAreaFormBean.getInstId());
		instInpatientArea.setInpatientArea(instInpatientAreaFormBean.getInpatientArea());
		instInpatientArea.setIntroduce(instInpatientAreaFormBean.getIntroduce());
		this.instInpatientAreaService.update(instInpatientArea);
	}
}
