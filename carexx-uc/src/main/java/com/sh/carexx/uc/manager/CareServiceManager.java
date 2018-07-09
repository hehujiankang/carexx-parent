package com.sh.carexx.uc.manager;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.CareServiceFormBean;
import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareService;
import com.sh.carexx.model.uc.InstCareService;
import com.sh.carexx.uc.service.CareServiceService;
import com.sh.carexx.uc.service.InstCareServiceService;

/**
 * 
 * ClassName: CareServiceManager <br/>
 * Function: 平台和机构服务信息 <br/>
 * Date: 2018年5月29日 下午4:46:21 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class CareServiceManager {
	@Autowired
	private CareServiceService careServiceService;
	@Autowired
	private InstCareServiceService instCareServiceService;

	/**
	 * 
	 * add:(添加平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param careServiceFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void add(CareServiceFormBean careServiceFormBean) throws BizException {
		CareService careService = this.careServiceService.getByworkTypeIdAndserviceName(
				careServiceFormBean.getWorkTypeId(), careServiceFormBean.getServiceName());
		if (careService != null) {
			throw new BizException(ErrorCode.CARE_SERVICE_EXISTS_ERROR);
		}
		careService = new CareService();
		careService.setWorkTypeId(careServiceFormBean.getWorkTypeId());
		careService.setServiceName(careServiceFormBean.getServiceName());
		careService.setServiceStatus(UseStatus.ENABLED.getValue());
		this.careServiceService.save(careService);
	}

	/**
	 * 
	 * modfiy:(修改平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param careServiceFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modfiy(CareServiceFormBean careServiceFormBean) throws BizException {
		CareService careService = this.careServiceService.getByworkTypeIdAndserviceName(
				careServiceFormBean.getWorkTypeId(), careServiceFormBean.getServiceName());
		if (careService != null && careService.getId() != careServiceFormBean.getId()) {
			throw new BizException(ErrorCode.CARE_SERVICE_EXISTS_ERROR);
		}
		careService = new CareService();
		careService.setId(careServiceFormBean.getId());
		careService.setWorkTypeId(careServiceFormBean.getWorkTypeId());
		careService.setServiceName(careServiceFormBean.getServiceName());
		this.careServiceService.update(careService);
	}

	/**
	 * 
	 * enable:(启用平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void enable(Integer id) throws BizException {
		this.careServiceService.updateServiceStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	/**
	 * 
	 * disable:(停用平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void disable(Integer id) throws BizException {
		this.careServiceService.updateServiceStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}

	/**
	 * 
	 * addInstCareService:(添加机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param instCareServiceFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void addInstCareService(InstCareServiceFormBean instCareServiceFormBean) throws BizException {
		InstCareService instCareService = this.instCareServiceService.queryExistence(instCareServiceFormBean);
		if (instCareService != null) {
			throw new BizException(ErrorCode.INST_CARE_SERVICE_EXISTS_ERROR);
		}
		instCareService = new InstCareService();
		instCareService.setInstId(instCareServiceFormBean.getInstId());
		instCareService.setServiceId(instCareServiceFormBean.getServiceId());
		instCareService.setServiceUnit(instCareServiceFormBean.getServiceUnit());
		instCareService.setServicePrice(new BigDecimal(instCareServiceFormBean.getServicePrice()));
		instCareService.setServiceStatus(UseStatus.ENABLED.getValue());
		this.instCareServiceService.save(instCareService);
	}

	/**
	 * 
	 * modifyInstCareService:(修改机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param instCareServiceFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modifyInstCareService(InstCareServiceFormBean instCareServiceFormBean) throws BizException {
		InstCareService instCareService = this.instCareServiceService.queryExistence(instCareServiceFormBean);
		if (instCareService != null && instCareService.getId() != instCareServiceFormBean.getId()) {
			throw new BizException(ErrorCode.INST_CARE_SERVICE_EXISTS_ERROR);
		}
		instCareService.setId(instCareServiceFormBean.getId());
		instCareService.setServiceUnit(instCareServiceFormBean.getServiceUnit());
		instCareService.setServicePrice(new BigDecimal(instCareServiceFormBean.getServicePrice()));
		this.instCareServiceService.update(instCareService);
	}
}
