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

import com.sh.carexx.bean.worktype.InstWorkTypeSettleFormBean;
import com.sh.carexx.bean.worktype.WorkTypeSettleQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.InstWorkTypeSettle;
import com.sh.carexx.uc.manager.InstWorkTypeSettleManager;
import com.sh.carexx.uc.service.InstWorkTypeSettleService;

@RestController
@RequestMapping("/instworktypesettle")
public class InstWorkTypeSettleController {
	@Autowired
	private InstWorkTypeSettleService instWorkTypeSettleService;
	@Autowired
	private InstWorkTypeSettleManager instWorkTypeSettleManager;

	@RequestMapping(value = "/list_all/{instId}/{workTypeId}", method = RequestMethod.GET)
	public String queryAllAvailable(@PathVariable("instId") Integer instId,
			@PathVariable("workTypeId") Integer workTypeId) {
		List<InstWorkTypeSettle> result = this.instWorkTypeSettleService.queryAllAvailable(instId, workTypeId);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, result).toJSON();
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean) {
		Integer totalNum = this.instWorkTypeSettleService.getInstWorkTypeSettleCount(workTypeSettleQueryFormBean);
		List<Map<?, ?>> result = null;
		if (totalNum > 0) {
			result = this.instWorkTypeSettleService.queryInstWorkTypeSettleList(workTypeSettleQueryFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, result)).toJSON();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody InstWorkTypeSettleFormBean instWorkTypeSettleFormBean) {
		try {
			this.instWorkTypeSettleManager.add(instWorkTypeSettleFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody InstWorkTypeSettleFormBean instWorkTypeSettleFormBean) {
		try {
			this.instWorkTypeSettleManager.modify(instWorkTypeSettleFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		try {
			this.instWorkTypeSettleManager.enable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		try {
			this.instWorkTypeSettleManager.disable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
