package com.sh.carexx.common.limit;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.limit.annotation.RequestLimit;
import com.sh.carexx.common.limit.exception.RequestLimitException;
import com.sh.carexx.common.util.WebUtils;

@Aspect
@Component
public class RequestLimitContract {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Before("within(com.sh.carexx.*.controller.*) && @annotation(requestLimit)")
	public void requestLimit(final JoinPoint joinPoint, RequestLimit requestLimit) throws RequestLimitException {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String ip = WebUtils.getIpAddr(request);
		String url = request.getRequestURL().toString();
		String key = CarexxConstant.CachePrefix.CAREXX_CTRL_REQ_LIMIT.concat(url).concat(ip);
		long count = 0L;
		try {
			count = this.redisTemplate.opsForValue().increment(key, 1);
			if (count == 1) {
				this.redisTemplate.expire(key, requestLimit.time(), TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			throw new RequestLimitException();
		}
		if (count > requestLimit.count()) {
			throw new RequestLimitException();
		}
	}
}
