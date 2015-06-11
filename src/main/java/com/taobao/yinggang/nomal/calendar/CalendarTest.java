/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.calendar;

import java.util.Calendar;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class CalendarTest {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		// cal.setTimeZone(TimeZone.getTimeZone(TimeZoneData.SHANGHAI.getKey()));
		// today
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1; // month is zero based
		int d = cal.get(Calendar.DAY_OF_MONTH);
		// String startDate = fillInt(m) + "/" + fillInt(d) + "/" + y;
		System.out.println("today:" + y + " / " + m + " / " + d + " / ");
		// end in a month from now
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH) + 1; // month is zero based
		d = cal.get(Calendar.DAY_OF_MONTH);
		// String endDate = fillInt(m) + "/" + fillInt(d) + "/" + y;
		System.out.println("today:" + y + " / " + m + " / " + d + " / ");
	}
}
