package com.sh.carexx.uc.manager;

import java.util.HashMap;
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
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.HttpClientUtils;
import com.sh.carexx.common.util.JSONUtils;

@Service
public class SmsManager {
	private static final String SMS_TYPE_VERIFY_CODE = "1";
	private static final String SMS_TYPE_NOTITY = "2";
	private static final String SMS_CONTENT_TYPE = "1";
	private static final int SMS_VERIFY_CODE_EXPIRE = 5;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${sms.gateway.serviceUrl}")
	private String serviceUrl;
	@Value("${sms.user.userId}")
	private String userId;
	@Value("${sms.user.password}")
	private String password;
	@Value("${sms.content.signature}")
	private String signature;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void send(String busiType, String mobiles, String content, String signature) throws BizException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", this.userId);
		params.put("password", this.password);
		params.put("busiType", busiType);
		params.put("contentType", SMS_CONTENT_TYPE);
		params.put("mobiles", mobiles);
		params.put("signature", signature);
		String result = null;
		try {
			result = HttpClientUtils.post(this.serviceUrl, params);
		} catch (Exception e) {
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
		if (StringUtils.isBlank(result)) {
			throw new BizException(ErrorCode.SYS_ERROR);
		}
		Map<String, Object> resultMap = JSONUtils.parseToMap(result);
		int code = Integer.parseInt(String.valueOf(resultMap.get("code")));
		if (code != 200) {
			throw new BizException((String) resultMap.get("errorCode"), (String) resultMap.get("errorMsg"));
		}
	}

	public void sendNotify(String mobiles, String content, String signature) throws BizException {
		this.send(SMS_TYPE_NOTITY, mobiles, content, signature);
	}

	public void sendVerifyCode(String mobile) throws BizException {
		String verifyCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		this.logger.info("验证码[{}]已成功发送至手机[{}]", verifyCode, mobile);
		String verifyCodeSms = String.format("验证码%s", verifyCode);
		this.send(SMS_TYPE_VERIFY_CODE, mobile, verifyCodeSms, this.signature);
		this.redisTemplate.opsForValue().set(CarexxConstant.CachePrefix.CAREXX_SMS_VERIFY_CODE + mobile, verifyCode,
				SMS_VERIFY_CODE_EXPIRE * 60, TimeUnit.SECONDS);
	}

	public void checkSmsVerifyCode(String mobile, String verifyCode) throws BizException {
		String storedVerifyCode = this.redisTemplate.opsForValue()
				.get(CarexxConstant.CachePrefix.CAREXX_SMS_VERIFY_CODE + mobile);
		if (StringUtils.isBlank(storedVerifyCode)) {
			throw new BizException(ErrorCode.SMS_VERIFY_CODE_INVALID);
		}
		if (!storedVerifyCode.equals(verifyCode)) {
			throw new BizException(ErrorCode.SMS_VERIFY_CODE_INPUT_ERROR);
		}
		this.redisTemplate.delete(CarexxConstant.CachePrefix.CAREXX_SMS_VERIFY_CODE + mobile);
	}
}
