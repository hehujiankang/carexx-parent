package com.sh.carexx.uc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.acl.AclLoginFormBean;
import com.sh.carexx.bean.user.OAuthLoginFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.uc.manager.AclUserManager;
import com.sh.carexx.uc.manager.UserManager;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AclUserManager aclUserManager;

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody AclLoginFormBean aclLoginFormBean) {
		try {
			String token = this.aclUserManager.login(aclLoginFormBean);
			return new DataRetVal(CarexxConstant.RetCode.SUCCESS, token).toJSON();
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc()).toJSON();
		}
	}

	@RequestMapping(value = "/get_session_user_id", method = RequestMethod.GET)
	public String getSessionUserId(@RequestParam("token") String token) {
		return this.aclUserManager.getSessionUserId(token);
	}

	@RequestMapping(value = "/oauth_login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String doOAuthLogin(@RequestBody OAuthLoginFormBean oAuthLoginFormBean) {
		try {
			String token = this.userManager.doOAuthLogin(oAuthLoginFormBean);
			return new DataRetVal(CarexxConstant.RetCode.SUCCESS, token).toJSON();
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc()).toJSON();
		}
	}

	@RequestMapping(value = "/get_oauth_user_id", method = RequestMethod.GET)
	public String getOAuthUserId(@RequestParam("token") String token) {
		return this.userManager.getOAuthUserId(token);
	}
}
