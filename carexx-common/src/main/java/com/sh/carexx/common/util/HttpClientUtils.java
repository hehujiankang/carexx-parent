package com.sh.carexx.common.util;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public final class HttpClientUtils {
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final int DEFAULT_CONN_TIMEOUT = 20000;
	public static final int DEFAULT_SO_TIMEOUT = 20000;
	public static final int DEFAULT_SEND_RETRY_TIMES = 3;

	/**
	 * 
	 * Function:创建可关闭ssl客户端 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static CloseableHttpClient createSSLClientDefault() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) {
				return true;
			}
		}).build();
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(DEFAULT_SO_TIMEOUT)
				.setConnectTimeout(DEFAULT_CONN_TIMEOUT).setConnectionRequestTimeout(DEFAULT_CONN_TIMEOUT).build();
		DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(DEFAULT_SEND_RETRY_TIMES,
				false);
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
				.setDefaultRequestConfig(requestConfig).setRetryHandler(retryHandler).build();
		return closeableHttpClient;
	}

	/**
	 * 
	 * Function:创建可关闭客户端 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static CloseableHttpClient createClientDefault() {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(DEFAULT_SO_TIMEOUT)
				.setConnectTimeout(DEFAULT_CONN_TIMEOUT).setConnectionRequestTimeout(DEFAULT_CONN_TIMEOUT).build();
		DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(DEFAULT_SEND_RETRY_TIMES,
				false);
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.setRetryHandler(retryHandler).build();
		return closeableHttpClient;
	}

	/**
	 * 
	 * Function:创建http客户端 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年6月8日上午11:41:59 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	private static CloseableHttpClient getCloseableHttpClient(boolean isSSL) throws Exception {
		if (isSSL) {
			return createSSLClientDefault();
		} else {
			return createClientDefault();
		}
	}

	/**
	 * 
	 * Function:请求文本流 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String execute(CloseableHttpClient httpClient, HttpContext context, HttpRequestBase requestBase,
			String charset) throws Exception {
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(requestBase, context);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				return EntityUtils.toString(httpEntity, charset);
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Function:请求文本流，默认编码 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String execute(CloseableHttpClient httpClient, HttpContext context, HttpRequestBase requestBase)
			throws Exception {
		return execute(httpClient, context, requestBase, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * Function:请求文本流，指定编码 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String execute(CloseableHttpClient httpClient, HttpRequestBase requestBase, String charset)
			throws Exception {
		return execute(httpClient, null, requestBase, charset);
	}

	/**
	 * 
	 * Function:请求字节流 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static byte[] execute4Stream(CloseableHttpClient httpClient, HttpContext context,
			HttpRequestBase requestBase, String charset) throws Exception {
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(requestBase, context);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				return EntityUtils.toByteArray(httpEntity);
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * Function:请求文本流，默认编码 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static byte[] execute4Stream(CloseableHttpClient httpClient, HttpContext context,
			HttpRequestBase requestBase) throws Exception {
		return execute4Stream(httpClient, context, requestBase, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * Function:请求文本流 ，指定编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static byte[] execute4Stream(CloseableHttpClient httpClient, HttpRequestBase requestBase, String charset)
			throws Exception {
		return execute4Stream(httpClient, null, requestBase, charset);
	}

	/**
	 * 
	 * Function:发送get请求，指定编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String get(String url, Map<String, String> headers, Map<String, String> params, boolean isSSL,
			String charset) throws Exception {
		CloseableHttpClient httpClient = getCloseableHttpClient(isSSL);
		HttpGet get = new HttpGet(UrlUtils.createFullUrl(url, params));
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				get.setHeader(entry.getKey(), entry.getValue());
			}
		}
		return execute(httpClient, null, get, charset);
	}

	/**
	 * 
	 * Function:发送post请求，默认编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String get(String url, Map<String, String> params) throws Exception {
		return get(url, null, params, false, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * Function:发送http get请求，默认编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String get(String url) throws Exception {
		return get(url, null, null, false, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * Function:发送post请求，默认编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String post(String url, Map<String, String> headers, Map<String, String> params, boolean isSSL,
			String charset) throws Exception {
		CloseableHttpClient httpClient = getCloseableHttpClient(isSSL);
		HttpPost post = new HttpPost(url);
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());
			}
		}
		if (params != null && params.size() > 0) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			post.setEntity(new UrlEncodedFormEntity(nvps, charset));
		}
		return execute(httpClient, null, post, charset);
	}

	/**
	 * 
	 * Function:发送get请求, 指定编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String post(String url, Map<String, String> headers, String body, boolean isSSL, String charset)
			throws Exception {
		CloseableHttpClient httpClient = getCloseableHttpClient(isSSL);
		HttpPost post = new HttpPost(url);
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());
			}
		}
		post.setEntity(new StringEntity(body, charset));
		return execute(httpClient, null, post, charset);
	}

	/**
	 * 
	 * Function:发送http post请求，提交参数， 默认编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String post(String url, Map<String, String> params) throws Exception {
		return post(url, null, params, false, DEFAULT_CHARSET);
	}

	/**
	 * 
	 * Function:发送http post请求，提交内容， 默认编码<br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月10日下午12:06:13 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public static String post(String url, String body) throws Exception {
		return post(url, null, body, false, DEFAULT_CHARSET);
	}
}