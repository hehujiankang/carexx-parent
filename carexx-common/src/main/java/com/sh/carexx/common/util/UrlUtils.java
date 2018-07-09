package com.sh.carexx.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * 
 * ClassName: URL工具类 <br/>
 * Function: TODO <br/>
 * Date: 2017年3月10日 下午10:52:25 <br/>
 *
 * @author wanglong
 * @version
 * @since JDK 1.7
 */
public final class UrlUtils {
	public static final String DEFAULT_URL_ENCODING = "utf-8";

	/**
	 * 
	 * Function:URL编码 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月10日下午10:52:38 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String encode(String url) {
		try {
			return URLEncoder.encode(url, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	/**
	 * 
	 * Function:URL解码 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年3月10日下午10:52:56 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String decode(String url) {
		try {
			return URLDecoder.decode(url, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	/**
	 * 
	 * Function:构建http参数uri <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:03:06 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String createUri(Map<String, String> params, boolean isSort) {
		StringBuilder sb = new StringBuilder();
		Object[] keys = params.keySet().toArray();
		if (isSort) {
			Arrays.sort(keys);
		}
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append("=").append(params.get(keys[i]));
			if (i < keys.length - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * Function:构建http完整url <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日上午11:54:46 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String createFullUrl(String baseUrl, Map<String, String> params) {
		if (params != null && !params.isEmpty()) {
			return baseUrl + "?" + createUri(params, false);
		}
		return baseUrl;
	}
}
