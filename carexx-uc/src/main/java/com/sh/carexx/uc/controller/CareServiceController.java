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

import com.sh.carexx.bean.care.CareServiceFormBean;
import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.CareService;
import com.sh.carexx.uc.manager.CareServiceManager;
import com.sh.carexx.uc.service.CareServiceService;
import com.sh.carexx.uc.service.InstCareServiceService;

@RestController
@RequestMapping("/careservice")
public class CareServiceController {
	@Autowired
	private CareServiceManager careServiceManager;

	@Autowired
	private CareServiceService careServiceService;

	@Autowired
	private InstCareServiceService instCareServiceService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody CareServiceFormBean careServiceFromBean) {
		try {
			this.careServiceManager.add(careServiceFromBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list_all", method = RequestMethod.GET)
	public String queryAllCareService() {
		List<CareService> result = this.careServiceService.queryAllAvailable();
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, result).toJSON();
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody InstServiceQueryFormBean instServiceQueryFormBean) {
		Integer totalNum = this.careServiceService.getCareServiceCount(instServiceQueryFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.careServiceService.queryCareServiceList(instServiceQueryFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modfiy(@RequestBody CareServiceFormBean careServiceFormBean) {
		try {
			this.careServiceManager.modfiy(careServiceFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		try {
			this.careServiceManager.enable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		try {
			this.careServiceManager.disable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/add_inst", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal addInstCareService(@RequestBody InstCareServiceFormBean instCareServiceFormBean) {
		try {
			this.careServiceManager.addInstCareService(instCareServiceFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/modify_inst", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modifyInstCareService(@RequestBody InstCareServiceFormBean instCareServiceFormBean) {
		try {
			this.careServiceManager.modifyInstCareService(instCareServiceFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list_inst", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryInstCareServiceForList(@RequestBody InstServiceQueryFormBean instServiceQueryFormBean) {
		Integer totalNum = this.instCareServiceService.getInstCareServiceCount(instServiceQueryFormBean);
		List<Map<?, ?>> result = null;
		if (totalNum > 0) {
			result = this.instCareServiceService.queryInstCareServiceList(instServiceQueryFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, result)).toJSON();
	}

	@RequestMapping(value = "/list_all_inst", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryAllAvailableInstCareService(@RequestBody InstServiceQueryFormBean instServiceQueryFormBean) {
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, instCareServiceService.queryAllAvailable(instServiceQueryFormBean)).toJSON();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public BasicRetVal deleteInstCareService(@PathVariable("id") Integer id) {
		try {
			this.instCareServiceService.delete(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
