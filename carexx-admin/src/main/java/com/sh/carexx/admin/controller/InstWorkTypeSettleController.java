package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.worktype.InstWorkTypeSettleFormBean;
import com.sh.carexx.bean.worktype.WorkTypeSettleQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/instworktypesettle")
public class InstWorkTypeSettleController extends BaseController {

	@RequestMapping(value = "/list_all/{workTypeId}")
	public String queryAllAvailable(@PathVariable("workTypeId") Integer workTypeId) {
		Integer instId = this.getCurrentUser().getInstId();
		return this.ucServiceClient.queryAllInstWorkTypeSettle(instId, workTypeId);
	}

	@RequestMapping(value = "/list")
	public String queryForList(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean) {
		workTypeSettleQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstWorkTypeSettleForList(workTypeSettleQueryFormBean);
	}

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstWorkTypeSettleFormBean instWorkTypeSettleFormBean, BindingResult bindingResult) {
		instWorkTypeSettleFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstWorkTypeSettle(instWorkTypeSettleFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstWorkTypeSettleFormBean instWorkTypeSettleFormBean,
			BindingResult bindingResult) {
		instWorkTypeSettleFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (instWorkTypeSettleFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstWorkTypeSettle(instWorkTypeSettleFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableInstWorkTypeSettle(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableInstWorkTypeSettle(id);
	}
}
