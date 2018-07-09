package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.dict.DictFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid DictFormBean dictFormBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addDict(dictFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid DictFormBean dictFormBean, BindingResult bindingResult) {
		if (dictFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyDict(dictFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(DictFormBean dictFormBean) {
		return this.ucServiceClient.queryDictForList(dictFormBean);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableDict(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableDict(id);
	}
}
