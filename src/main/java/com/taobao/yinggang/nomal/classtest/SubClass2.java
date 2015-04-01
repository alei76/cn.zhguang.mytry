/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.classtest;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class SubClass2 extends SupperClass {
	public SubClass2(int a, String test) {
		super(a, test);
		// TODO Auto-generated constructor stub
	}

	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	public void print() {
		System.out.println("" + a + " " + test);
	}

	public void change() {
		a = 20;
		test = "test2-2";
	}
}
