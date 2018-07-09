package com.sh.carexx.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class BeanUtils {
	private static final String METHOD_GET = "get";
	private static final String METHOD_SET = "set";
	private static final String CLASS = "class";

	public static Object getPropertyValueByName(String propertyName, Object inst) {
		try {
			return invokeInstMethod(getMethodName(METHOD_GET, propertyName), inst, new Object[] {}, new Class[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setPropertyValueByName(String propertyName, Object inst, Object[] values,
			Class<?>... parameterTypes) {
		try {
			invokeInstMethod(getMethodName(METHOD_SET, propertyName), inst, values, parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object invokeInstMethod(String methodName, Object inst, Object[] values, Class<?>... parameterTypes) {
		try {
			Method method = inst.getClass().getMethod(methodName, parameterTypes);
			return method.invoke(inst, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object invokeStaticMethod(String methodName, Class<?> clazz, Object[] values,
			Class<?>... parameterTypes) {
		try {
			Method method = clazz.getMethod(methodName, parameterTypes);
			return method.invoke(clazz, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getMethodName(String prefix, String propertyName) {
		char firstWord = propertyName.charAt(0);
		char upperWord = Character.toUpperCase(firstWord);
		if (firstWord == upperWord) {
			return prefix + propertyName;
		}
		byte[] bytes = propertyName.getBytes();
		bytes[0] = (byte) (bytes[0] + ('A' - 'a'));
		return prefix + new String(bytes);
	}

	public static void copyBeanField(Object src, Object target, String filedName) {
		Object value = getPropertyValueByName(filedName, src);
		if (value != null) {
			setPropertyValueByName(filedName, target, new Object[] { value }, value.getClass());
		}
	}

	public static Map<String, Object> bean2Map(Object bean) {
		Class<?> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!CLASS.equals(propertyName)) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					returnMap.put(propertyName, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	public static Object map2Bean(Map<String, Object> map, Class<?> clazz) {
		Object obj = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			obj = clazz.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
