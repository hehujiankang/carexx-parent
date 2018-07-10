package com.sh.carexx.mapp.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.carexx.bean.user.OAuthLoginFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.enums.user.IdentityType;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.JSONUtils;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.mapp.wechat.WechatManager;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.application.domain}")
	private String domain;

	@Autowired
	private WechatManager wechatManager;

	@RequestMapping("/login")
	public String login(String code) {
		String openId = null;
		String token = null;
		try {
			Map<String, Object> oAuthInfo = this.wechatManager.getOAuthInfo(code);
			openId = String.valueOf(oAuthInfo.get("openid"));
			String accessToken = String.valueOf(oAuthInfo.get("access_token"));
			Map<String, Object> userInfo = this.wechatManager.getUserInfo(accessToken, openId);
			OAuthLoginFormBean oAuthLoginFormBean = new OAuthLoginFormBean();
			oAuthLoginFormBean.setIdentityType(IdentityType.WECHAT.getValue());
			oAuthLoginFormBean.setIdentifier(openId);
			oAuthLoginFormBean.setNickname(String.valueOf(userInfo.get("nickname")));
			oAuthLoginFormBean.setAvatar(String.valueOf(userInfo.get("headimgurl")));
			String gender = String.valueOf(userInfo.get("sex"));
			if (StringUtils.isNotBlank(gender)) {
				oAuthLoginFormBean.setSex(Byte.parseByte(gender));
			}
			String province = String.valueOf(userInfo.get("province"));
			if (StringUtils.isNotBlank(province)) {
				oAuthLoginFormBean.setRegion(province);
			}
			String city = String.valueOf(userInfo.get("city"));
			if (StringUtils.isNotBlank(city)) {
				oAuthLoginFormBean.setRegion(oAuthLoginFormBean.getRegion() + city);
			}
			String retVal = this.ucServiceClient.doOAuthLogin(oAuthLoginFormBean);
			DataRetVal dataRetVal = JSONUtils.toBean(retVal, DataRetVal.class);
			if (dataRetVal != null && CarexxConstant.RetCode.SUCCESS == dataRetVal.getCode()) {
				token = String.valueOf(dataRetVal.getData());
			}
		} catch (BizException e) {
			this.logger.error("微信登录失败", e);
		}
		return "redirect:" + this.domain + "/index.html#/tab/wechat/home?token=" + token + "&openId=" + openId;
	}
}
