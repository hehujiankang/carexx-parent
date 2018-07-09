package com.sh.carexx.mapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerorderschedule")
public class CustomerOrderScheduleController extends BaseController {

	@RequestMapping(value = "/all_schedule/{orderNo}")
	public String queryOrderScheduleByOrderNo(@PathVariable("orderNo") String orderNo) {
		return this.ucServiceClient.queryOrderScheduleByOrderNo(orderNo);
	}
}
