package com.sh.carexx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * ClassName: 日期工具类 <br/>
 * Function: TODO <br/>
 * Date: 2017年5月20日 下午6:18:36 <br/>
 *
 * @author wanglong
 * @version
 * @since JDK 1.7
 */
public final class DateUtils {
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDD = "yyMMdd";
	public static final String HHMMSS = "HHmmss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static String toString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	public static String toString(String pattern) {
		return toString(new Date(), pattern);
	}

	public static Date toDate(String date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	public static Date addDay(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date addHour(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static int getHourDiff(Date startTime, Date endTime) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(startTime);
		end.setTime(endTime);
		long timestamps = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (timestamps / 1000 / 3600);
	}

	public static int getHourOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
}
