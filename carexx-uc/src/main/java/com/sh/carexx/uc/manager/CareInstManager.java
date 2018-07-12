package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInst;
import com.sh.carexx.uc.service.CareInstService;

/**
 * 
 * ClassName: CareInstManager <br/> 
 * Function: 医疗机构 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 上午11:28:31 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class CareInstManager {

	@Autowired
	public CareInstService careInstService;
	
	/**
	 * 
	 * add:(医疗机构新增). <br/> 
	 * 
	 * @author zhoulei 
	 * @param careInstFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(CareInstFormBean careInstFormBean) throws BizException {
		//机构名称校验
		if (careInstService.getByInstName(careInstFormBean) != null) {
			throw new BizException(ErrorCode.INST_EXISTS_ERROR);
		} else {
			CareInst careInst = new CareInst();
			careInst.setInstType(careInstFormBean.getInstType());
			careInst.setInstName(careInstFormBean.getInstName());
			careInst.setInstStatus(UseStatus.ENABLED.getValue());
			careInst.setInstRegion(careInstFormBean.getInstRegion());
			careInst.setInstAddr(careInstFormBean.getInstAddr());
			careInst.setInstLng(new Double(careInstFormBean.getInstLng()));
			careInst.setInstLat(new Double(careInstFormBean.getInstLng()));
			careInst.setIntroduce(careInstFormBean.getIntroduce());

			this.careInstService.save(careInst);
		}
	}

	/**
	 * 
	 * modify:(医疗机构修改). <br/> 
	 * 
	 * @author zhoulei 
	 * @param careInstFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void modify(CareInstFormBean careInstFormBean) throws BizException {
		//机构名称校验
		CareInst oldcareInst = careInstService.getByInstName(careInstFormBean);
		if (oldcareInst != null && oldcareInst.getId() != careInstFormBean.getId()) {
			throw new BizException(ErrorCode.INST_NAME_EXISTS_ERROR);
		} else {
			CareInst careInst = new CareInst();
			careInst.setId(careInstFormBean.getId());
			careInst.setInstType(careInstFormBean.getInstType());
			careInst.setInstName(careInstFormBean.getInstName());
			careInst.setInstRegion(careInstFormBean.getInstRegion());
			careInst.setInstAddr(careInstFormBean.getInstAddr());
			careInst.setInstLng(new Double(careInstFormBean.getInstLng()));
			careInst.setInstLat(new Double(careInstFormBean.getInstLat()));
			careInst.setIntroduce(careInstFormBean.getIntroduce());

			this.careInstService.update(careInst);
		}
	}

	/**
	 * 
	 * enable:(医疗机构启用). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void enable(Integer id) throws BizException {
		this.careInstService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	/**
	 * 
	 * disable:(医疗机构禁用). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void disable(Integer id) throws BizException {
		this.careInstService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
