package com.sh.carexx.uc.manager;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.worktype.InstWorkTypeSettleFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstWorkTypeSettle;
import com.sh.carexx.uc.service.InstWorkTypeSettleService;

/**
 * 
 * ClassName: InstWorkTypeSettleManager <br/>
 * Function: 工种结算信息 <br/>
 * Date: 2018年5月29日 下午4:54:36 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class InstWorkTypeSettleManager {
	@Autowired
	private InstWorkTypeSettleService instWorkTypeSettleService;

	/**
	 * 
	 * add:(添加工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instWorkTypeSettleFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void add(InstWorkTypeSettleFormBean instWorkTypeSettleFormBean) throws BizException {
		InstWorkTypeSettle instWorkTypeSettle = this.instWorkTypeSettleService.queryByExistence(
				instWorkTypeSettleFormBean.getInstId(), instWorkTypeSettleFormBean.getWorkTypeId(),
				new BigDecimal(instWorkTypeSettleFormBean.getSettleRatio()));
		if (instWorkTypeSettle != null) {
			throw new BizException(ErrorCode.WORK_TYPE_SETTLE_EXISTS_ERROR);
		}
		if (new BigDecimal(instWorkTypeSettleFormBean.getSettleRatio()).compareTo(new BigDecimal(1)) > 0) {
			throw new BizException(ErrorCode.WORK_TYPE_SETTLE_RATIO_INPUT_ERROR);
		}
		instWorkTypeSettle = new InstWorkTypeSettle();
		instWorkTypeSettle.setInstId(instWorkTypeSettleFormBean.getInstId());
		instWorkTypeSettle.setWorkTypeId(instWorkTypeSettleFormBean.getWorkTypeId());
		instWorkTypeSettle.setSettleRatio(new BigDecimal(instWorkTypeSettleFormBean.getSettleRatio()));
		instWorkTypeSettle.setSettleStatus(UseStatus.ENABLED.getValue());
		this.instWorkTypeSettleService.save(instWorkTypeSettle);
	}

	/**
	 * 
	 * modify:(修改工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param instWorkTypeSettleFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modify(InstWorkTypeSettleFormBean instWorkTypeSettleFormBean) throws BizException {
		InstWorkTypeSettle instWorkTypeSettle = this.instWorkTypeSettleService.queryByExistence(
				instWorkTypeSettleFormBean.getInstId(), instWorkTypeSettleFormBean.getWorkTypeId(),
				new BigDecimal(instWorkTypeSettleFormBean.getSettleRatio()));
		if (instWorkTypeSettle != null && instWorkTypeSettle.getId() != instWorkTypeSettleFormBean.getId()) {
			throw new BizException(ErrorCode.WORK_TYPE_SETTLE_EXISTS_ERROR);
		}
		if (new BigDecimal(instWorkTypeSettleFormBean.getSettleRatio()).compareTo(new BigDecimal(1)) > 0) {
			throw new BizException(ErrorCode.WORK_TYPE_SETTLE_RATIO_INPUT_ERROR);
		}
		instWorkTypeSettle = new InstWorkTypeSettle();
		instWorkTypeSettle.setId(instWorkTypeSettleFormBean.getId());
		instWorkTypeSettle.setInstId(instWorkTypeSettleFormBean.getInstId());
		instWorkTypeSettle.setWorkTypeId(instWorkTypeSettleFormBean.getWorkTypeId());
		instWorkTypeSettle.setSettleRatio(new BigDecimal(instWorkTypeSettleFormBean.getSettleRatio()));
		this.instWorkTypeSettleService.update(instWorkTypeSettle);
	}

	/**
	 * 
	 * enable:(启用工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void enable(Integer id) throws BizException {
		this.instWorkTypeSettleService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	/**
	 * 
	 * disable:(停用工种结算信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void disable(Integer id) throws BizException {
		this.instWorkTypeSettleService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
