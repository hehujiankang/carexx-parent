package com.sh.carexx.uc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.uc.manager.SmsManager;

@RestController
@RequestMapping("/sms")
public class SmsController {
	@Autowired
	private SmsManager smsManager;

	@RequestMapping(value = "/send_verify_code/{mobile}", method = RequestMethod.GET)
	public BasicRetVal sendVerifyCode(@PathVariable("mobile") String mobile) {
		try {
			this.smsManager.sendVerifyCode(mobile);
			return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
	}
}
