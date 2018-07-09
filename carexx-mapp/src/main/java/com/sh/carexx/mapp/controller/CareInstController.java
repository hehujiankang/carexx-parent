package com.sh.carexx.mapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.care.CareInstFormBean;

@RestController
@RequestMapping("/careinst")
public class CareInstController extends BaseController {

	@RequestMapping(value = "/all")
	public String queryForAll(CareInstFormBean careInstFormBean) {
		return this.ucServiceClient.queryAllCareInst(careInstFormBean);
	}
}
