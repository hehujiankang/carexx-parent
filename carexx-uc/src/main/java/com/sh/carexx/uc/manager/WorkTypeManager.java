package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.bean.worktype.WorkTypeFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.WorkType;
import com.sh.carexx.uc.service.WorkTypeService;

/**
 * 
 * ClassName: WorkTypeManager <br/>
 * Function: 工种信息 <br/>
 * Date: 2018年5月29日 下午4:56:15 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class WorkTypeManager {
	@Autowired
	private WorkTypeService workTypeService;

	/**
	 * 
	 * add:(添加工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workTypeFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void add(WorkTypeFormBean workTypeFormBean) throws BizException {

		WorkType workType = this.workTypeService.getByWorkTypeName(workTypeFormBean.getWorkTypeName());
		if (workType != null) {
			throw new BizException(ErrorCode.WORK_TYPE_EXISTS_ERROR);
		}
		workType = new WorkType();
		workType.setWorkTypeName(workTypeFormBean.getWorkTypeName());
		workType.setWorkTypeStatus(UseStatus.ENABLED.getValue());
		this.workTypeService.save(workType);
	}

	/**
	 * 
	 * modify:(修改工种信息). <br/>
	 * 
	 * @author hetao
	 * @param workTypeFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modify(WorkTypeFormBean workTypeFormBean) throws BizException {
		WorkType workType = this.workTypeService.getByWorkTypeName(workTypeFormBean.getWorkTypeName());
		if (workType != null && workType.getId() != workTypeFormBean.getId()) {
			throw new BizException(ErrorCode.WORK_TYPE_EXISTS_ERROR);
		}
		workType = new WorkType();
		workType.setId(workTypeFormBean.getId());
		workType.setWorkTypeName(workTypeFormBean.getWorkTypeName());
		this.workTypeService.update(workType);
	}

	/**
	 * 
	 * enable:(启用工种信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void enable(Integer id) throws BizException {
		this.workTypeService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	/**
	 * 
	 * disable:(停用工种信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void disable(Integer id) throws BizException {
		this.workTypeService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
