package com.sh.carexx.common.web;

import com.sh.carexx.common.util.Radix32Utils;

public class SessionHolder {
	private static ThreadLocal<String> sessionUserIds = new ThreadLocal<String>();

	public static void setUserId(String userId) {
		sessionUserIds.set(userId);
	}

	public static Integer getUserId() {
		return Radix32Utils.decodeInt(sessionUserIds.get());
	}
}
