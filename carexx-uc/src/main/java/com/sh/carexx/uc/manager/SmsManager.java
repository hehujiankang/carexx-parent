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

/**
 * 
 * ClassName: SmsManager <br/> 
 * Function: 短信发送管理器 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月4日 上午10:31:53 <br/> 
 * 
 * @author WL 
 * @since JDK 1.8
 */
@Service
public class SmsManager {
	/** 验证码短信 */
	private static final String SMS_TYPE_VERIFY_CODE = "1";
	/** 通知短信 */
	private static final String SMS_TYPE_NOTITY = "2";
	/** 内容类型 */
	private static final String SMS_CONTENT_TYPE = "1";
	/** 验证码有效期（分钟） */
	private static final int SMS_VERIFY_CODE_EXPIRE = 5;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//发送地址
	@Value("${sms.gateway.serviceUrl}")
	private String serviceUrl;
	//用户ID
	@Value("${sms.user.userId}")
	private String userId;
	//密钥
	@Value("${sms.user.password}")
	private String password;
	//默认短信签名
	@Value("${sms.content.signature}")
	private String signature;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 
	 * send:发送短信 <br/> 
	 * @author WL 
	 * @param busiType
	 * @param mobiles（多个手机号用英文逗号隔开）
	 * @param content
	 * @param signature
	 * @throws BizException 
	 * @since JDK 1.8
	 */
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

	/**
	 * 
	 * sendNotify:发送通知短信 <br/> 
	 * @author WL 
	 * @param mobiles
	 * @param content
	 * @param signature
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void sendNotify(String mobiles, String content, String signature) throws BizException {
		this.send(SMS_TYPE_NOTITY, mobiles, content, signature);
	}

	/**
	 * 
	 * sendVerifyCode:发送验证码 <br/> 
	 * @author WL 
	 * @param mobile
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void sendVerifyCode(String mobile) throws BizException {
		String verifyCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		this.logger.info("验证码[{}]已成功发送至手机[{}]", verifyCode, mobile);
		String verifyCodeSms = String.format("验证码%s", verifyCode);
		this.send(SMS_TYPE_VERIFY_CODE, mobile, verifyCodeSms, this.signature);
		this.redisTemplate.opsForValue().set(CarexxConstant.CachePrefix.CAREXX_SMS_VERIFY_CODE + mobile, verifyCode,
				SMS_VERIFY_CODE_EXPIRE * 60, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * checkSmsVerifyCode:检查短信验证码 <br/> 
	 * @author WL 
	 * @param mobile
	 * @param verifyCode
	 * @throws BizException 
	 * @since JDK 1.8
	 */
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
