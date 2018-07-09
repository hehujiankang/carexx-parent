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

import com.sh.carexx.bean.customer.InstCustomerFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.uc.manager.InstCustomerManager;
import com.sh.carexx.uc.service.InstCustomerService;

@RestController
@RequestMapping("/instcustomer")
public class InstCustomerController {
	@Autowired
	private InstCustomerService instCustomerService;

	@Autowired
	private InstCustomerManager instCustomerManager;

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody InstCustomerFormBean instCustomerFormBean) {
		Integer totalNum = this.instCustomerService.getInstCustomerCount(instCustomerFormBean);
		List<Map<?, ?>> result = null;
		if (totalNum > 0) {
			result = this.instCustomerService.queryInstCustomerList(instCustomerFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, result)).toJSON();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody InstCustomerFormBean instCustomerFormBean) {
		try {
			this.instCustomerManager.add(instCustomerFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody InstCustomerFormBean instCustomerFormBean) {
		try {
			this.instCustomerManager.modify(instCustomerFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		try {
			this.instCustomerService.delete(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
