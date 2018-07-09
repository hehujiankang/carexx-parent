package com.sh.carexx.common.util;

public final class HexUtils {
	private static final String HEX_META = "0123456789ABCDEF";

	private static byte charToByte(char c) {
		return (byte) HEX_META.indexOf(c);
	}

	public static String bytes2HexString(byte[] src) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		return sb.toString();
	}

	public static byte[] hexString2Bytes(String hexString) {
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	public static String hexString2String(String hexString) {
		return new String(hexString2Bytes(hexString));
	}

	public static String string2HexString(String strPart) {
		return bytes2HexString(strPart.getBytes());
	}
}