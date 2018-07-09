package com.sh.carexx.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sh.carexx.api.client.UCServiceClient;
import com.sh.carexx.common.util.WebUtils;
import com.sh.carexx.common.web.SessionHolder;

public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private UCServiceClient ucServiceClient;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		String token = WebUtils.getAuthToken(req);
		if (StringUtils.isBlank(token)) {
			res.sendError(WebUtils.UNAUTH);
			return false;
		}
		String userId = this.ucServiceClient.getSessionUserId(token);
		if (StringUtils.isBlank(userId)) {
			res.sendError(WebUtils.UNAUTH);
			return false;
		}
		SessionHolder.setUserId(userId);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
