package com.sh.carexx.mapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping(value = "/sms")
public class SmsController extends BaseController {

	@RequestMapping(value = "/send_verify_code/{mobile}")
	public BasicRetVal sendVerifyCode(@PathVariable("mobile") String mobile){
		return this.ucServiceClient.sendVerifyCode(mobile);
	}
}
