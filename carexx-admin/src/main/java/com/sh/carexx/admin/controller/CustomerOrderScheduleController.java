package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.order.CustomerOrderScheduleFormBean;
import com.sh.carexx.bean.order.OrderSettleAdjustAmtFormBean;
import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/customerorderschedule")
public class CustomerOrderScheduleController extends BaseController {

	@RequestMapping("/all")
	public String queryAll() {
		return this.ucServiceClient.queryAllForCustomerOrderSchedule();
	}

	@RequestMapping("/add")
	public BasicRetVal add(@Valid CustomerOrderScheduleFormBean customerOrderScheduleFormBean,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCustomerOrderSchedule(customerOrderScheduleFormBean);
	}

	@RequestMapping("/all_schedule/{orderNo}")
	public String queryScheduleByOrderNo(@PathVariable("orderNo") String orderNo) {
		return this.ucServiceClient.queryOrderScheduleByOrderNo(orderNo);
	}

	@RequestMapping("/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Long id) {
		return this.ucServiceClient.deleteCustomerOrderSchedule(id);
	}

	@RequestMapping("/confirmcompleted/{id}")
	public BasicRetVal confirmCompleted(@PathVariable("id") Long id) {
		return this.ucServiceClient.confirmCompletedCustomerOrderSchedule(id);
	}

	@RequestMapping(value = "/batchdelete")
	public BasicRetVal batchdeleteschedule(String ids) {
		if (StringUtils.isBlank(ids)) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.batchDeleteSchedule(ids);
	}

	@RequestMapping(value = "/batchconfirmcompleted")
	public BasicRetVal batchConfirmCompleteSchedule(String ids) {
		if (StringUtils.isBlank(ids)) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.batchConfirmCompleteSchedule(ids);
	}

	@RequestMapping(value = "/workquantity_report")
	public String queryWorkQuantityReport(WorkQuantityReportFormBean workQuantityReportFormBean) {
		workQuantityReportFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryWorkQuantityReport(workQuantityReportFormBean);
	}

	@RequestMapping(value = "/modify_settle_amt")
	public BasicRetVal modifySettleAmt(@Valid OrderSettleAdjustAmtFormBean OrderSettleAdjustAmtFormBean,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifySettleAmt(OrderSettleAdjustAmtFormBean);
	}
}
