package com.sh.carexx.uc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.order.CustomerOrderScheduleFormBean;
import com.sh.carexx.bean.order.OrderSettleAdjustAmtFormBean;
import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.uc.manager.CustomerOrderScheduleManager;
import com.sh.carexx.uc.service.CustomerOrderScheduleService;

@RestController
@RequestMapping("/customerorderschedule")
public class CustomerOrderScheduleController {
	@Autowired
	private CustomerOrderScheduleService customerOrderScheduleService;

	@Autowired
	private CustomerOrderScheduleManager customerOrderScheduleManager;

	@RequestMapping(value = "/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryAll() {
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS,
				this.customerOrderScheduleService.queryCustomerOrderSchedule()).toJSON();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody CustomerOrderScheduleFormBean customerOrderScheduleFormBean) {
		try {
			this.customerOrderScheduleManager.add(customerOrderScheduleFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/all_schedule/{orderNo}", method = RequestMethod.GET)
	public String queryScheduleByOrderNo(@PathVariable("orderNo") String orderNo) {
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS,
				this.customerOrderScheduleService.queryScheduleByOrderNo(orderNo)).toJSON();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public BasicRetVal delete(@PathVariable("id") Long id) {
		try {
			this.customerOrderScheduleManager.delete(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/confirmcompleted/{id}", method = RequestMethod.GET)
	public BasicRetVal confirmCompleted(@PathVariable("id") Long id) {
		try {
			this.customerOrderScheduleManager.confirmCompleted(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
	public BasicRetVal batchDeleteSchedule(@RequestParam("ids") String ids) {
		try {
			this.customerOrderScheduleManager.batchDelete(ids);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/batchconfirmcompleted", method = RequestMethod.POST)
	public BasicRetVal batchConfirmCompleteSchedule(@RequestParam("ids") String ids) {
		try {
			this.customerOrderScheduleManager.batchConfirmComplete(ids);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/workquantity_report", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryWorkQuantityReport(@RequestBody WorkQuantityReportFormBean workQuantityReportFormBean) {
		List<Map<String, Object>> result = this.customerOrderScheduleService
				.queryWorkQuantityReport(workQuantityReportFormBean);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, result).toJSON();
	}

	@RequestMapping(value = "/modify_settle_amt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modifySettleAmt(@RequestBody OrderSettleAdjustAmtFormBean orderSettleAdjustAmtFormBean) {
		try {
			this.customerOrderScheduleManager.modifySettleAmt(orderSettleAdjustAmtFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
