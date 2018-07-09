package com.sh.carexx.mapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/inpatientarea")
public class InstInpatientAreaController  extends BaseController {

	@RequestMapping(value = "/all/{instId}")
	public String queryForAll(@PathVariable("instId") Integer instId) {
		return this.ucServiceClient.queryAllInstInpatientArea(instId);
	}
}
