package com.sh.carexx.common.util;

import java.util.Random;

public final class Radix32Utils {
	private static final int RADIX_32 = 32;
	private static final char[] RADIX_ELEMENTS = new char[] { 'q', 'W', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9',
			'c', '7', 'P', '5', 'i', 'K', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'Y', 'l', 't', 'n', '6', 'b', 'g',
			'H' };
	private static final char RADIX_ELEMENT_O = 'o';

	private static final int RADIX_ELEMENTS_SIZE = RADIX_ELEMENTS.length;

	public static String encode(long id, int strlen) {
		char[] buf = new char[RADIX_32];
		int charPos = RADIX_32;
		while ((id / RADIX_ELEMENTS_SIZE) > 0) {
			int ind = (int) (id % RADIX_ELEMENTS_SIZE);
			buf[--charPos] = RADIX_ELEMENTS[ind];
			id /= RADIX_ELEMENTS_SIZE;
		}
		buf[--charPos] = RADIX_ELEMENTS[(int) (id % RADIX_ELEMENTS_SIZE)];
		String str = new String(buf, charPos, (32 - charPos));
		if (str.length() < strlen) {
			StringBuilder sb = new StringBuilder();
			sb.append(RADIX_ELEMENT_O);
			Random rnd = new Random();
			for (int i = 1; i < strlen - str.length(); i++) {
				sb.append(RADIX_ELEMENTS[rnd.nextInt(RADIX_ELEMENTS_SIZE)]);
			}
			str += sb.toString();
		}
		return str;
	}

	public static String encode(long id) {
		return encode(id, RADIX_32);
	}

	public static String encode(int id) {
		return encode(id, RADIX_32);
	}

	public static long decode(String code) {
		char chs[] = code.toCharArray();
		long res = 0;
		for (int i = 0; i < chs.length; i++) {
			int ind = 0;
			for (int j = 0; j < RADIX_ELEMENTS_SIZE; j++) {
				if (chs[i] == RADIX_ELEMENTS[j]) {
					ind = j;
					break;
				}
			}
			if (chs[i] == RADIX_ELEMENT_O) {
				break;
			}
			if (i > 0) {
				res = res * RADIX_ELEMENTS_SIZE + ind;
			} else {
				res = ind;
			}
		}
		return res;
	}

	public static int decodeInt(String code) {
		return (int) decode(code);
	}
}
