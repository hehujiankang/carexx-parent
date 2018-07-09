package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/holiday")
public class InstHolidayController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstHolidayFormBean instHolidayFormBean, BindingResult bindingResult) {
		instHolidayFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstHoliday(instHolidayFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstHolidayFormBean instHolidayFormBean) {
		instHolidayFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		return this.ucServiceClient.queryInstHolidayForList(instHolidayFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstHolidayFormBean instHolidayFormBean, BindingResult bindingResult) {
		instHolidayFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (instHolidayFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstHoliday(instHolidayFormBean);
	}
	
	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Long id) {
		return this.ucServiceClient.deleteInstHoliday(id);
	}

}
