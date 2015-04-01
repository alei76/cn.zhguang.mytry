/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.string;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class StringTest {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String aString = "";
		// String[] bs = aString.split(",");
		// System.out.println(bs.length);
		// for (String s : bs) {
		// System.out.print(s + " ");
		// }

		//		String task = "13367359,";
		//		String[] taskGroup = task.split(",");
		//		String entityId = taskGroup[0];
		//		String scheduleId = taskGroup[1];
		//		Long entityIdValue = null;
		//		Long scdIdValue = null;
		//		entityIdValue = Long.parseLong(entityId);
		//		scdIdValue = Long.parseLong(scheduleId);
		Long status = 324324932998423L;
		System.out.println(ToStringBuilder.reflectionToString(status, ToStringStyle.SHORT_PREFIX_STYLE));

	}
}
