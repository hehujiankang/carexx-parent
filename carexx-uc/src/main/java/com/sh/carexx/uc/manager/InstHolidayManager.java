package com.sh.carexx.uc.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.InstHoliday;
import com.sh.carexx.uc.service.InstHolidayService;

/**
 * 
 * ClassName: InstHolidayManager <br/> 
 * Function: 节假日 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午1:26:39 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class InstHolidayManager {

	@Autowired
	private InstHolidayService instHolidayService;

	/**
	 * 
	 * add:(节假日添加). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instHolidayFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(InstHolidayFormBean instHolidayFormBean) throws BizException {
		//节假日校验
		if (instHolidayService.getOrderByExistence(instHolidayFormBean).size() > 0) {
			throw new BizException(ErrorCode.INST_HOLIDAY_EXISTS_ERROR);
		}
		
		InstHoliday instHoliday = new InstHoliday();
		instHoliday.setInstId(instHolidayFormBean.getInstId());
		instHoliday.setHolidayName(instHolidayFormBean.getHolidayName());
		if (ValidUtils.isDate(instHolidayFormBean.getHolidayStartTime())) {
			instHoliday.setHolidayStartTime(
					DateUtils.toDate(instHolidayFormBean.getHolidayStartTime(), DateUtils.YYYY_MM_DD));
		}
		if (ValidUtils.isDate(instHolidayFormBean.getHolidayEndTime())) {
			instHoliday
					.setHolidayEndTime(DateUtils.toDate(instHolidayFormBean.getHolidayEndTime(), DateUtils.YYYY_MM_DD));
		}
		instHoliday.setHolidayStatus(UseStatus.ENABLED.getValue());

		this.instHolidayService.save(instHoliday);
	}

	/**
	 * 
	 * modify:(节假日修改). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instHolidayFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void modify(InstHolidayFormBean instHolidayFormBean) throws BizException {
		//节假日校验
		boolean flag = false;
		List<InstHoliday> InstHolidayList = instHolidayService.getOrderByExistence(instHolidayFormBean);
		for (InstHoliday instHoliday : InstHolidayList) {
			if (InstHolidayList.size() > 1 && instHoliday.getId() == instHolidayFormBean.getId()) {
				flag = true;
			}
		}
		if (InstHolidayList != null && flag == true) {
			throw new BizException(ErrorCode.INST_HOLIDAY_EXISTS_ERROR);
		}

		InstHoliday instHoliday = new InstHoliday();
		instHoliday.setId(instHolidayFormBean.getId());
		instHoliday.setInstId(instHolidayFormBean.getInstId());
		instHoliday.setHolidayName(instHolidayFormBean.getHolidayName());
		if (ValidUtils.isDate(instHolidayFormBean.getHolidayStartTime())) {
			instHoliday.setHolidayStartTime(
					DateUtils.toDate(instHolidayFormBean.getHolidayStartTime(), DateUtils.YYYY_MM_DD));
		}
		if (ValidUtils.isDate(instHolidayFormBean.getHolidayEndTime())) {
			instHoliday
					.setHolidayEndTime(DateUtils.toDate(instHolidayFormBean.getHolidayEndTime(), DateUtils.YYYY_MM_DD));
		}

		this.instHolidayService.update(instHoliday);
	}
}
