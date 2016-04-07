package com.cmcc.tool.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xxx
 */
public class BeanConvert {
	
	/**
	 * 将javaBeanPoJo转换为map
	 * @param javaBean javaBean类
	 * @return 对应的map
	 */
	public static Map<String, String> toMap(Object javaBean) {
		Map<String, String> result = new HashMap<String, String>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					if (!field.equals("IDValue")) {
						field = field.toLowerCase().charAt(0) + field.substring(1);
					}
					

					Object value = method.invoke(javaBean, (Object[]) null);
					if (null == value) {
						continue;
					}
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
			}
		}

		return result;
	}

	/**
	 * 将map转换为对应的POJO类
	 * @param javabean 对应的类
	 * @param data map值
	 * @return 组装好后的javabean
	 */
	public static Object toJavaBean(Object javabean, Map<String, String> data) {
		Method[] methods = javabean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
//					field = field.toLowerCase().charAt(0) + field.substring(1);
					if (!field.equals("IDValue")) {
						field = field.toLowerCase().charAt(0) + field.substring(1);
					}
					method.invoke(javabean, new Object[] { data.get(field) });
				}
			} catch (Exception e) {
			}
		}

		return javabean;
	}

}
