package com.sh.carexx.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.sh.carexx.common.CarexxConstant;

public final class ValidUtils {
	public static boolean doRegexValid(String regex, String src) {
		if (StringUtils.isBlank(regex) || StringUtils.isBlank(src)) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		return m.matches();
	}

	public static boolean isEmail(String email) {
		return doRegexValid(CarexxConstant.Regex.EMAIL, email);
	}

	public static boolean isPhone(String phone) {
		return doRegexValid(CarexxConstant.Regex.PHONE, phone);
	}

	public static boolean isMobile(String mobile) {
		return doRegexValid(CarexxConstant.Regex.MOBILE, mobile);
	}

	public static boolean isNumber(String number) {
		return doRegexValid(CarexxConstant.Regex.NUMBER, number);
	}

	public static boolean isInteger(String integer) {
		return doRegexValid(CarexxConstant.Regex.INTEGER, integer);
	}

	public static boolean isDouble(String dbl) {
		return doRegexValid(CarexxConstant.Regex.DOUBLE, dbl);
	}

	public static boolean isIP(String ip) {
		return doRegexValid(CarexxConstant.Regex.IP, ip);
	}

	public static boolean isURL(String url) {
		return doRegexValid(CarexxConstant.Regex.SIMPLE_URL, url);
	}

	public static boolean isIDCardNo(String idCardNo) {
		return doRegexValid(CarexxConstant.Regex.ID_CARD_NO, idCardNo);
	}

	public static boolean isWordNum(String wordNum) {
		return doRegexValid(CarexxConstant.Regex.WORD_NUM, wordNum);
	}

	public static boolean isDate(String date) {
		return doRegexValid(CarexxConstant.Regex.DATE, date);
	}
	
	public static boolean isDateTime(String datetime){
		return doRegexValid(CarexxConstant.Regex.DATETIME, datetime);
	}

	public static boolean isOrgCode(String orgCode) {
		return doRegexValid(CarexxConstant.Regex.ORG_CODE, orgCode);
	}

	public static boolean isAge(String age) {
		return doRegexValid(CarexxConstant.Regex.AGE, age);
	}

	public static boolean isChineseName(String name) {
		return doRegexValid(CarexxConstant.Regex.CHINESE_NAME, name);
	}
}
