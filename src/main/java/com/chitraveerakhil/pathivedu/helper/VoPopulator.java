package com.chitraveerakhil.pathivedu.helper;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;

public class VoPopulator<T, R> {

	/*
	 * Get all the fields from the Original Class and the Inspired Class If the
	 * original class field is present in the Inspired Class then copy the
	 * fields
	 */

	private static final Logger Log = LoggerFactory.getLogger(VoPopulator.class);

	public R populateObject(T origin, R populated) {
		Log.debug("Origin Class::" + origin.getClass());
		Log.debug("Populated Class::" + populated.getClass());
		Class<?> originClass = origin.getClass();
		Class<?> populatedClass = populated.getClass();
		Map<String, Method> originatedtedMethods = fetchMethodsInMap(originClass, UtilConstants.STR_GET);
		Map<String, Method> populatedMethods = fetchMethodsInMap(populatedClass, UtilConstants.STR_SET);

		originatedtedMethods.forEach((key, getterMethod) -> {
			String fieldName = getFieldName(getterMethod);

			if (populatedMethods.get(fieldName) != null)
				setField(origin, populated, getterMethod, populatedMethods.get(fieldName));
		});
		return populated;

	}

	private String getFieldName(Method method) {
		if (method.getName().startsWith(UtilConstants.STR_IS))
			return Introspector.decapitalize(method.getName().substring(2));
		return Introspector.decapitalize(method.getName().substring(3));
	}

	private void setField(T origin, R populated, Method getMethod, Method setMethod) {
		try {

			if (getMethod.getReturnType().equals(Date.class) && setMethod.getParameterTypes()[0].equals(String.class)) {
				Object object = getMethod.invoke(origin);
				setMethod.invoke(populated, UtilConstants.dateToStr((Date) object));
			} else if (getMethod.getReturnType().equals(String.class)
					&& setMethod.getParameterTypes()[0].equals(Date.class)) {
				Object object = getMethod.invoke(origin);
				setMethod.invoke(populated, UtilConstants.strToDate((String) object));
			} else {
				Object object = getMethod.invoke(origin);
				setMethod.invoke(populated, object);
			}
			Log.debug("Fields set::" + populated);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Map<String, Method> fetchMethodsInMap(Class<?> clazz, String str) {
		Map<String, Method> methods = new HashMap<>();
		for (Method method : clazz.getMethods()) {
			if (str.equals(UtilConstants.STR_GET)
					&& (method.getName().startsWith(str) || method.getName().startsWith(UtilConstants.STR_IS))) {
				String fieldName = getFieldName(method);
				methods.put(fieldName, method);
			} else if (method.getName().startsWith(str) && method.getName().startsWith(UtilConstants.STR_SET)) {
				String fieldName = getFieldName(method);
				methods.put(fieldName, method);
			}
		}
		return methods;
	}

}
