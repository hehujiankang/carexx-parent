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

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.uc.manager.CareInstManager;
import com.sh.carexx.uc.service.CareInstService;

@RestController
@RequestMapping("/careinst")
public class CareInstController {

	@Autowired
	private CareInstService careInstService;

	@Autowired
	private CareInstManager careInstManager;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody CareInstFormBean careInstFormBean) {
		try {
			this.careInstManager.add(careInstFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody CareInstFormBean careInstFormBean) {
		Integer totalNum = this.careInstService.getCareInstCount(careInstFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.careInstService.queryCareInstList(careInstFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/list_service", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryServiceForList(@RequestBody CareInstFormBean careInstFormBean) {
		Integer totalNum = this.careInstService.getCareInstCount(careInstFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.careInstService.queryCareInstList(careInstFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForAll(@RequestBody CareInstFormBean careInstFormBean) {
		List<Map<?, ?>> resultList = null;
		resultList = this.careInstService.queryAllCareInst(careInstFormBean);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, resultList).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody CareInstFormBean careInstFormBean) {
		try {
			this.careInstManager.modify(careInstFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		try {
			this.careInstManager.enable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		try {
			this.careInstManager.disable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

}
