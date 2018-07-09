package com.sh.carexx.mapp.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.HttpClientUtils;
import com.sh.carexx.common.util.JAXBUtils;
import com.sh.carexx.common.util.JSONUtils;
import com.sh.carexx.common.util.UUIDUtils;
import com.sh.carexx.common.util.UrlUtils;
import com.sh.carexx.common.wechat.AesException;
import com.sh.carexx.common.wechat.WXBizMsgCrypt;
import com.sh.carexx.mapp.wechat.bean.WechatNotifyMsg;
import com.sh.carexx.mapp.wechat.bean.WechatReplyNewsArticle;
import com.sh.carexx.mapp.wechat.bean.WechatReplyNewsMsg;
import com.sh.carexx.mapp.wechat.bean.WechatReplyTextMsg;

@Service
public class WechatManager {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String MSG_TYPE_EVENT = "event";
	public static final String MSG_TYPE_TEXT = "text";
	public static final String MSG_TYPE_NEWS = "news";

	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	public static final String EVENT_SCAN = "SCAN";

	public static final String OAUTH_SNSAPI_BASE = "snsapi_base";
	public static final String OAUTH_SNSAPI_USERINFO = "snsapi_userinfo";

	@Value("${wechat.appId}")
	private String appId;
	@Value("${wechat.appSecret}")
	private String appSecret;
	@Value("${wechat.token}")
	private String token;
	@Value("${wechat.encodingAesKey}")
	private String encodingAesKey;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData)
			throws BizException {
		try {
			WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(this.token, this.encodingAesKey, this.appId);
			return wxBizMsgCrypt.decryptMsg(msgSignature, timeStamp, nonce, postData);
		} catch (AesException e) {
			this.logger.error("微信消息解密失败", e);
			throw new BizException(e);
		}
	}

	public String processMsg(String msgSignature, String timeStamp, String nonce, String postData) throws BizException {
		if (StringUtils.isBlank(postData)) {
			return null;
		}
		this.logger.info("收到微信推送消息原文：[{}]", postData);
		String plainText = this.decryptMsg(msgSignature, timeStamp, nonce, postData);
		this.logger.info("收到微信推送消息明文：[{}]", plainText);
		WechatNotifyMsg wechatMsg = null;
		try {
			wechatMsg = JAXBUtils.convert2JavaBean(plainText, WechatNotifyMsg.class);
		} catch (Exception e) {
			throw new BizException(e);
		}
		if (wechatMsg == null || StringUtils.isBlank(wechatMsg.getFromUserName())) {
			throw new BizException();
		}
		return this.handleMsg(wechatMsg);
	}

	private String handleMsg(WechatNotifyMsg wechatMsg) {
		if (MSG_TYPE_EVENT.equals(wechatMsg.getMsgType())) {
			if (EVENT_SUBSCRIBE.equals(wechatMsg.getEvent())) {
				return this.handleSubscribeEvent(wechatMsg);
			} else if (EVENT_SCAN.equals(wechatMsg.getEvent())) {
				return this.handleScanEvent(wechatMsg);
			}
		}
		return null;
	}

	private String handleSubscribeEvent(WechatNotifyMsg wechatMsg) {
		return null;
	}

	private String handleScanEvent(WechatNotifyMsg wechatMsg) {
		return null;
	}

	private Map<String, Object> getOAuthInfo(String code, String appId, String secret) throws BizException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("appid", appId);
		params.put("secret", secret);
		params.put("code", code);
		this.logger.info("获取OAuth信息开始，参数：[{}]", JSONUtils.toString(params));
		Map<String, Object> resultMap = null;
		try {
			String result = HttpClientUtils.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
			this.logger.info("获取OAuth信息响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			this.logger.error("获取OAuth信息失败", e);
			throw new BizException(e);
		}
		Integer errcode = (Integer) resultMap.get("errcode");
		if (errcode != null && errcode != 0) {
			this.logger.error("获取OAuth信息失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
			throw new BizException();
		}
		return resultMap;
	}

	public Map<String, Object> getOAuthInfo(String code) throws BizException {
		return this.getOAuthInfo(code, this.appId, this.appSecret);
	}

	private Map<String, Object> getWxAppletOAuthInfo(String code, String appId, String secret) throws BizException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("appid", appId);
		params.put("secret", secret);
		params.put("js_code", code);
		this.logger.info("获取小程序OAuth信息开始，参数：[{}]", JSONUtils.toString(params));
		Map<String, Object> resultMap = null;
		try {
			String result = HttpClientUtils.get("https://api.weixin.qq.com/sns/jscode2session", params);
			this.logger.info("获取小程序OAuth信息响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			this.logger.error("获取小程序OAuth信息失败", e);
			throw new BizException(e);
		}
		Integer errcode = (Integer) resultMap.get("errcode");
		if (errcode != null && errcode != 0) {
			this.logger.error("获取小程序OAuth信息失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
			throw new BizException();
		}
		return resultMap;
	}

	private String getOpenId(String code, String appId, String secret) throws BizException {
		Map<String, Object> resultMap = this.getOAuthInfo(code, appId, secret);
		return (String) resultMap.get("openid");
	}

	public String getOpenId(String code) throws BizException {
		return this.getOpenId(code, this.appId, this.appSecret);
	}

	public String getWxAppletOpenId(String code, String appId, String secret) throws BizException {
		Map<String, Object> resultMap = this.getWxAppletOAuthInfo(code, appId, secret);
		return (String) resultMap.get("openid");
	}

	public Map<String, Object> getUserInfo(String accessToken, String openid) throws BizException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		params.put("lang", "zh_CN");
		this.logger.info("获取微信用户信息开始，参数：[{}]", JSONUtils.toString(params));
		Map<String, Object> resultMap = null;
		try {
			String result = HttpClientUtils.get("https://api.weixin.qq.com/sns/userinfo", params);
			this.logger.info("获取微信用户信息响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			throw new BizException(e);
		}
		Integer errcode = (Integer) resultMap.get("errcode");
		if (errcode != null && errcode != 0) {
			this.logger.error("获取微信用户信息失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
			throw new BizException();
		}
		return resultMap;
	}

	private String getAccessToken() throws BizException {
		String accessToken = this.redisTemplate.opsForValue()
				.get(CarexxConstant.CachePrefix.CAREXX_WECHAT_ACCESS_TOKEN + this.appId);
		if (StringUtils.isNotBlank(accessToken)) {
			return accessToken;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", this.appId);
		params.put("secret", this.appSecret);
		Map<String, Object> resultMap = null;
		try {
			String result = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token", params);
			this.logger.info("获取AccessToken响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			this.logger.error("获取AccessToken失败", e);
			throw new BizException(e);
		}
		if (resultMap == null || resultMap.isEmpty()) {
			throw new BizException();
		}
		accessToken = (String) resultMap.get("access_token");
		if (StringUtils.isBlank(accessToken)) {
			this.logger.error("获取AccessToken失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
			throw new BizException();
		}
		// 提前一分钟失效，重新获取新的AccessToken
		Integer expiresIn = (Integer) resultMap.get("expires_in") - 1 * 60;
		this.redisTemplate.opsForValue().set(CarexxConstant.CachePrefix.CAREXX_WECHAT_ACCESS_TOKEN + this.appId,
				accessToken, expiresIn, TimeUnit.SECONDS);
		return accessToken;
	}

	public void deleteWechatMenu() throws BizException {
		Map<String, Object> resultMap = null;
		try {
			String result = HttpClientUtils
					.get("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + this.getAccessToken());
			this.logger.info("删除微信菜单响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			this.logger.error("删除微信菜单失败", e);
			throw new BizException(e);
		}
		if (resultMap == null || resultMap.isEmpty()) {
			throw new BizException();
		}
		Integer errcode = (Integer) resultMap.get("errcode");
		if (errcode.intValue() != 0) {
			this.logger.error("删除微信菜单失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
			throw new BizException();
		}
	}

	public void createWechatMenu(Map<String, Object> params) throws BizException {
		Map<String, Object> resultMap = null;
		String postData = JSONUtils.toString(params);
		try {
			String result = HttpClientUtils.post(
					"https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + this.getAccessToken(), postData);
			this.logger.info("创建微信菜单响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			this.logger.error("创建微信菜单失败", e);
			throw new BizException(e);
		}
		if (resultMap == null || resultMap.isEmpty()) {
			throw new BizException();
		}
		Integer errcode = (Integer) resultMap.get("errcode");
		if (errcode.intValue() != 0) {
			this.logger.error("创建微信菜单失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
			throw new BizException();
		}
	}

	public String buildOAuthLink(String mode, String redirectUrl) {
		String urlFormat = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
		redirectUrl = UrlUtils.encode(redirectUrl);
		String state = UUIDUtils.getShortUUID();
		String oAuthLink = String.format(urlFormat, this.appId, redirectUrl, mode, state);
		this.logger.info("生成微信网页授权链接，授权模式：[{}]，返回地址：[{}]，链接地址：[{}]", mode, redirectUrl, oAuthLink);
		return oAuthLink;
	}

	public void sendTplMsg(String toUserName, String tplId, String url, Map<String, Object> data) throws BizException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("touser", toUserName);
		params.put("template_id", tplId);
		params.put("url", this.buildOAuthLink(OAUTH_SNSAPI_BASE, url));
		if (data != null && data.size() > 0) {
			Map<String, String> value = null;
			for (Map.Entry<String, Object> entry : data.entrySet()) {
				value = new HashMap<String, String>();
				value.put("value", (String) entry.getValue());
				value.put("color", "#173177");
				data.put(entry.getKey(), value);
			}
		}
		params.put("data", data);
		String postData = JSONUtils.toString(params);
		Map<String, Object> resultMap = null;
		try {
			String result = HttpClientUtils.post(
					"https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + this.getAccessToken(),
					postData);
			this.logger.info("发送模板消息响应：[{}]", result);
			resultMap = JSONUtils.parseToMap(result);
		} catch (Exception e) {
			throw new BizException(e);
		}
		if (resultMap == null || resultMap.isEmpty()) {
			throw new BizException();
		}
		if ((Integer) resultMap.get("errcode") != 0) {
			this.logger.error("发送模板消息失败，错误码[{}]，错误描述[{}]", resultMap.get("errcode"), resultMap.get("errmsg"));
		}
	}

	public String replyTextMsg(String fromUserName, String toUserName, String content) {
		WechatReplyTextMsg text = new WechatReplyTextMsg();
		text.setToUserName(toUserName);
		text.setFromUserName(fromUserName);
		String timeStamp = System.currentTimeMillis() + "";
		String nonce = UUIDUtils.getShortUUID();
		text.setCreateTime(timeStamp);
		text.setMsgType(MSG_TYPE_TEXT);
		text.setContent(content);
		try {
			WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(this.token, this.encodingAesKey, this.appId);
			String replyMsg = JAXBUtils.convert2Xml(text);
			this.logger.info("回复文本消息:[{}]", replyMsg);
			return wxBizMsgCrypt.encryptMsg(replyMsg, timeStamp, nonce);
		} catch (Exception e) {
			this.logger.error("回复文本消息失败", e);
		}
		return null;
	}

	public String replyNewsMsg(String fromUserName, String toUserName, List<WechatReplyNewsArticle> articles) {
		WechatReplyNewsMsg text = new WechatReplyNewsMsg();
		text.setToUserName(toUserName);
		text.setFromUserName(fromUserName);
		String timeStamp = System.currentTimeMillis() + "";
		String nonce = UUIDUtils.getShortUUID();
		text.setCreateTime(timeStamp);
		text.setMsgType(MSG_TYPE_NEWS);
		text.setArticleCount(articles.size() + "");
		text.setArticles(articles);
		try {
			WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(this.token, this.encodingAesKey, this.appId);
			String replyMsg = JAXBUtils.convert2Xml(text);
			this.logger.info("回复图文消息:[{}]", replyMsg);
			return wxBizMsgCrypt.encryptMsg(replyMsg, timeStamp, nonce);
		} catch (Exception e) {
			this.logger.error("回复图文消息失败", e);
		}
		return null;
	}
}
