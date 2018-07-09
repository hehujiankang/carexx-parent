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

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.InstInpatientArea;
import com.sh.carexx.uc.manager.InstInpatientAreaManager;
import com.sh.carexx.uc.service.InstInpatientAreaService;

@RestController
@RequestMapping("/inpatientarea")
public class InstInpatientAreaController {

	@Autowired
	private InstInpatientAreaManager instInpatientAreaManager;

	@Autowired
	private InstInpatientAreaService instInpatientAreaService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody InstInpatientAreaFormBean instInpatientAreaFormBean) {
		try {
			this.instInpatientAreaManager.add(instInpatientAreaFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody InstInpatientAreaFormBean instInpatientAreaFormBean) {
		Integer totalNum = this.instInpatientAreaService.getInstInpatientAreaCount(instInpatientAreaFormBean);
		List<Map<?, ?>> resultList = null;
		if (totalNum > 0) {
			resultList = this.instInpatientAreaService.queryInstInpatientAreaList(instInpatientAreaFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody InstInpatientAreaFormBean instInpatientAreaFormBean) {
		try {
			this.instInpatientAreaManager.modify(instInpatientAreaFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		try {
			this.instInpatientAreaService.delete(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/all/{instId}", method = RequestMethod.GET)
	public String queryAllAvailable(@PathVariable("instId") Integer instId) {
		List<InstInpatientArea> resultList = null;
		resultList = this.instInpatientAreaService.queryAllInstInpatientArea(instId);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, resultList).toJSON();
	}
}
