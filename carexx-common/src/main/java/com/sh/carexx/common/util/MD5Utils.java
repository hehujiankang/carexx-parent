package com.sh.carexx.common.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MD5Utils {
	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);
	public static final String CHARSET = "utf-8";
	public static final String MD5 = "MD5";
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	public static String encode(String plainText) {
		MessageDigest messageDigest = null;
		try {
			byte[] bytes = plainText.getBytes(CHARSET);
			messageDigest = MessageDigest.getInstance(MD5);
			messageDigest.update(bytes);
		} catch (Exception e) {
			logger.error("MD5编码失败", e);
			return null;
		}
		byte[] updateBytes = messageDigest.digest();
		int len = updateBytes.length;
		char[] myChar = new char[len * 2];
		int k = 0;
		for (int i = 0; i < len; i++) {
			byte byte0 = updateBytes[i];
			myChar[(k++)] = HEX_DIGITS[(byte0 >>> 4 & 0xF)];
			myChar[(k++)] = HEX_DIGITS[(byte0 & 0xF)];
		}
		return new String(myChar);
	}
}
