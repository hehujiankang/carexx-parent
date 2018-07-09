package com.sh.carexx.common.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public final class JSONUtils {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String toString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toBean(String jsonString, Class<T> beanClass) {
		try {
			return mapper.readValue(jsonString, beanClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> parseToMap(String jsonString) {
		return toBean(jsonString, Map.class);
	}

	public static <T> List<T> parseToList(String jsonString, Class<T> beanClass) {
		JavaType javaType = getCollectionType(List.class, beanClass);
		try {
			return (List<T>) mapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}
