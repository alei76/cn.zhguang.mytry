package com.taobao.yinggang.nomal.clazInstanceOf;

import java.lang.reflect.Field;

/**
 * 判断对象是否“值相等”的工具
 * 
 * @author <a href="mailto:yinggang.zg@alibaba-inc.com">yinggang.zg</a>
 * @version 1.0
 * @since modified by yinggang.zg, at : 2014年9月24日 下午4:52:20
 */
public class ObjectEqualsUtil {
	/**
	 * 默认比较，不排除任何字段
	 * 
	 * @version 1.0
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static <T> boolean equals(T o1, T o2) {
		if (o1 == null) {
			if (o2 == null) {
				return true;
			} else {
				return false;
			}
		}

		if (o1.equals(o2)) {
			return true;
		}

		if (!o1.getClass().equals(o2.getClass())) {
			return false;
		}

		Class<?> clz = o1.getClass();
		Field[] declaredFieldArray = clz.getDeclaredFields();
		for (Field field : declaredFieldArray) {
			if (field == null) {
				continue;
			}
			try {
				field.setAccessible(true);
				Object fieldValue1 = field.get(o1);
				Object fieldValue2 = field.get(o2);

			} catch (Exception e) {
			}
		}
		return true;
	}

}
