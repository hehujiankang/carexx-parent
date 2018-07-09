package com.sh.carexx.common.keygen;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.DateUtils;

@Component
public class KeyGenerator {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public String generateOrderNo() throws BizException {
		try {
			long count = this.redisTemplate.opsForValue().increment(CarexxConstant.CachePrefix.CAREXX_SEQ_ORDER, 1);
			String seqNo = StringUtils.leftPad(String.valueOf(count), 12, "0");
			return DateUtils.toString(DateUtils.YYYYMMDD) + seqNo.substring(5);
		} catch (Exception e) {
			throw new BizException(ErrorCode.CACHE_ERROR);
		}
	}
}
