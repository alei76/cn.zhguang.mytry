/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.regex;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class RegexTest {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "2011-02-03";
		System.out.print(str.matches("\\d{4}-\\d{2}-\\d{2}"));
	}
}
