package com.sh.carexx.common.util;

import java.util.UUID;

public final class UUIDUtils {
	public static String getFullUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getShortUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
