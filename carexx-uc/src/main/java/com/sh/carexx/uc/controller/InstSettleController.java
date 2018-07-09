package com.sh.carexx.uc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.order.InstSettleQueryFormBean;
import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.InstSettle;
import com.sh.carexx.uc.manager.InstSettleManager;
import com.sh.carexx.uc.service.InstSettleService;
import com.sh.carexx.uc.service.OrderSettleService;

@RestController
@RequestMapping("/instsettle")
public class InstSettleController {
	@Autowired
	private InstSettleService instSettleService;

	@Autowired
	private InstSettleManager instSettleManager;

	@Autowired
	private OrderSettleService orderSettleService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody InstSettle instSettle) {
		try {
			this.instSettleManager.add(instSettle);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody InstSettleQueryFormBean instSettleQueryFormBean) {
		Integer totalNum = this.instSettleService.getInstSettleCount(instSettleQueryFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.instSettleService.queryInstSettleList(instSettleQueryFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/closesettle/{id}/{modifier}", method = RequestMethod.GET)
	public BasicRetVal closeSettle(@PathVariable("id") Long id, @PathVariable("modifier") String modifier) {
		try {
			this.instSettleManager.closeSettle(id, modifier);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/opensettle/{id}/{modifier}", method = RequestMethod.GET)
	public BasicRetVal openSettle(@PathVariable("id") Long id, @PathVariable("modifier") String modifier) {
		try {
			this.instSettleManager.openSettle(id, modifier);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/settlecount_report", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryOrderSettleCount(@RequestBody WorkQuantityReportFormBean workQuantityReportFormBean) {
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS,
				this.orderSettleService.queryOrderSettleCount(workQuantityReportFormBean)).toJSON();
	}
}
