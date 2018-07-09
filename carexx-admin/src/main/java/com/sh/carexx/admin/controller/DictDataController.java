package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/dictdata")
public class DictDataController extends BaseController {

	@RequestMapping(value = "/list_all/{dictid}")
	public String queryAllAvailableForList(@PathVariable("dictid") Integer dictid) {
		return this.ucServiceClient.queryAllAvailableDictDataForList(dictid);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid DictDataFormBean dictDataFormBean, BindingResult bindingResult) {
		if (dictDataFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyDictData(dictDataFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(DictDataFormBean dictDataFormBean) {
		if (dictDataFormBean.getDictId() == null) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT).toJSON();
		}
		return this.ucServiceClient.queryDictDataForList(dictDataFormBean);
	}

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid DictDataFormBean dictDataFormBean, BindingResult bindingResult) {
		if (dictDataFormBean.getDictId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addDictData(dictDataFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableDictData(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableDictData(id);
	}
}
