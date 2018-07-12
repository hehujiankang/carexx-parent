package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstInpatientArea;
import com.sh.carexx.uc.service.InstInpatientAreaService;

/**
 * 
 * ClassName: InstInpatientAreaManager <br/> 
 * Function: 机构病区 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午1:30:55 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class InstInpatientAreaManager {

	@Autowired
	private InstInpatientAreaService instInpatientAreaService;

	/**
	 * 
	 * add:(病区添加). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
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

	/**
	 * 
	 * modify:(病区修改). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
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
