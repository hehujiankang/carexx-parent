package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInst;
import com.sh.carexx.uc.service.CareInstService;

@Service
public class CareInstManager {

	@Autowired
	public CareInstService careInstService;

	public void add(CareInstFormBean careInstFormBean) throws BizException {
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

	public void modify(CareInstFormBean careInstFormBean) throws BizException {
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

	public void enable(Integer id) throws BizException {
		this.careInstService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	public void disable(Integer id) throws BizException {
		this.careInstService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
