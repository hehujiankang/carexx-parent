package com.sh.carexx.mapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.InstServiceQueryFormBean;

@RestController
@RequestMapping("/careservice")
public class CareServiceController extends BaseController {
	@RequestMapping(value = "/list_all_inst")
	public String queryAllAvailableInstCareService(InstServiceQueryFormBean instServiceQueryFormBean) {
		instServiceQueryFormBean.setMapp(1);
		return this.ucServiceClient.queryAllAvailableInstCareService(instServiceQueryFormBean);
	}
}
