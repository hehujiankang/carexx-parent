package com.sh.carexx.mapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/careservice")
public class CareServiceController extends BaseController {
	@RequestMapping(value = "/list_all_inst")
	public String queryAllAvailableInstCareService(InstServiceQueryFormBean instServiceQueryFormBean) {
		if (instServiceQueryFormBean.getInstId() == null) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT).toJSON();
		}
		instServiceQueryFormBean.setMapp(1);
		return this.ucServiceClient.queryAllAvailableInstCareService(instServiceQueryFormBean);
	}
}
