package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.acl.AclLoginFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@RequestMapping("/login")
	public String login(@Valid AclLoginFormBean aclLoginFormBean, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT).toJSON();
		}
		return this.ucServiceClient.login(aclLoginFormBean);
	}
}
