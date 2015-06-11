/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.date;

import java.text.SimpleDateFormat;

/**
 * @author ggugzhg
 * @version $Revision: $
 */
public class DateUtil {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	public static boolean isValidDate(String s) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		try {
			dateFormat.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] arg) {
		System.out.print(DateUtil.isValidDate("2011-7-50"));

	}
}
