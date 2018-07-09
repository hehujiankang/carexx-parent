package com.sh.carexx.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public final class Base64Utils {
	private static final String CHARSET = "utf-8";

	public static byte[] encode(byte[] src) {
		return Base64.encodeBase64(src);
	}

	public static String encode(String src) {
		try {
			return new String(encode(src.getBytes(CHARSET)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static byte[] decode(byte[] src) {
		return Base64.decodeBase64(src);
	}

	public static String decode(String src) {
		try {
			return new String(decode(src.getBytes(CHARSET)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String encode2String(byte[] src) {
		try {
			return new String(encode(src), CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static byte[] decode2Bytes(String src) {
		try {
			return decode(src.getBytes(CHARSET));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
