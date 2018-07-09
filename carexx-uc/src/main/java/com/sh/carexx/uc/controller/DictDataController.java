package com.sh.carexx.uc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.DictData;
import com.sh.carexx.uc.manager.DictDataManager;
import com.sh.carexx.uc.service.DictDataService;

@RestController
@RequestMapping("/dictdata")
public class DictDataController {
	@Autowired
	private DictDataService dictDataService;
	@Autowired
	private DictDataManager dictDataManager;

	@RequestMapping(value = "/list_all/{dictid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryAllAvailableForList(@PathVariable("dictid") Integer dictid) {
		List<DictData> dictData = this.dictDataService.getAllAvailableByDictId(dictid);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, dictData).toJSON();
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody DictDataFormBean dictDataFormBean) {
		Integer totalNum = this.dictDataService.getDictDataCount(dictDataFormBean);
		List<DictData> result = null;
		if (totalNum > 0) {
			result = this.dictDataService.queryDictDataList(dictDataFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, result)).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody DictDataFormBean dictDataFormBean) {
		try {
			this.dictDataManager.modify(dictDataFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody DictDataFormBean dictDataFormBean) {
		try {
			this.dictDataManager.add(dictDataFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		try {
			this.dictDataManager.enable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		try {
			this.dictDataManager.disable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
