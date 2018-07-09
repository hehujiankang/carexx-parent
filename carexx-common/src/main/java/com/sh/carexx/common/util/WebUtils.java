package com.sh.carexx.common.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public final class WebUtils {
	public static final int UNAUTH = 401;
	public static final String TOKEN = "token";
	public static final String AUTH_TOKEN = "AUTH-TOKEN";
	public static final String UNKNOWN = "unknown";

	public static Map<String, String> getRequestParamMap(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			if (paramValues == null || paramValues.length == 0) {
				map.put(paramName, "");
			} else {
				map.put(paramName, paramValues[0]);
			}
		}
		return map;
	}

	public static String getAuthToken(HttpServletRequest req) {
		String token = req.getHeader(AUTH_TOKEN);
		if (StringUtils.isBlank(token)) {
			token = req.getParameter(TOKEN);
		}
		return token;
	}

	public static byte[] getRequestPostBody(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {
			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddr = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ipAddr) || UNKNOWN.equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipAddr) || UNKNOWN.equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipAddr) || UNKNOWN.equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ipAddr)) {
			ipAddr = ipAddr.split(",")[0];
		}
		return ipAddr;
	}

	public static String getUserAgent(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (StringUtils.isNotBlank(userAgent)) {
			userAgent = userAgent.substring(userAgent.lastIndexOf(")") + 1);
		}
		return userAgent;
	}
}
