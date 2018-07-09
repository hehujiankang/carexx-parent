package com.sh.carexx.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class LocationUtils {
	private static final double EARTH_RADIUS = 6378137;

	private static double degrees(double d) {
		return d * (180 / Math.PI);
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static List<LatlgtPoint> getPointsList(double lgt, double lat, double distance) {
		List<LatlgtPoint> pointsList = new ArrayList<LatlgtPoint>();
		double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS)) / Math.cos(rad(lat)));
		dlng = degrees(dlng);
		double dlat = distance / EARTH_RADIUS;
		dlat = degrees(dlat);
		// 左上角的顶点
		LatlgtPoint leftUpPoint = new LatlgtPoint();
		leftUpPoint.setLat(lat + dlat);
		leftUpPoint.setLgt(lgt - dlng);
		pointsList.add(leftUpPoint);
		// 左下角的顶点
		LatlgtPoint leftDownPoint = new LatlgtPoint();
		leftDownPoint.setLat(lat - dlat);
		leftDownPoint.setLgt(lgt - dlng);
		pointsList.add(leftDownPoint);
		// 右上角的顶点
		LatlgtPoint rightUpPoint = new LatlgtPoint();
		rightUpPoint.setLat(lat + dlat);
		rightUpPoint.setLgt(lgt + dlng);
		pointsList.add(rightUpPoint);
		// 右下角的顶点
		LatlgtPoint rightDownPoint = new LatlgtPoint();
		rightDownPoint.setLat(lat - dlat);
		rightDownPoint.setLgt(lgt + dlng);
		pointsList.add(rightDownPoint);
		return pointsList;
	}

	public static String calDistance(float lng1, float lat1, float lng2, float lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS / 1000;
		return String.format("%.2f", s);
	}

	static class LatlgtPoint {
		private double lat;
		private double lgt;

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLgt() {
			return lgt;
		}

		public void setLgt(double lgt) {
			this.lgt = lgt;
		}

		@Override
		public String toString() {
			return "LatlgtPoint [lat=" + lat + ", lgt=" + lgt + "]";
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getRegionByLocation(float lng, float lat) {
		Map<String, String> params = new HashMap<>();
		params.put("l", String.valueOf(lat) + "," + String.valueOf(lng));
		params.put("type", "010");
		String result = null;
		try {
			result = HttpClientUtils.get("http://gc.ditu.aliyun.com/regeocoding", params);
		} catch (Exception e) {
			return result;
		}
		if (StringUtils.isBlank(result)) {
			return result;
		}
		Map<String, Object> resultMap = JSONUtils.parseToMap(result);
		List<Map<String, String>> addrList = (List) resultMap.get("addrList");
		if (addrList.size() == 0) {
			return null;
		}
		return addrList.get(0).get("admName");
	}
}
