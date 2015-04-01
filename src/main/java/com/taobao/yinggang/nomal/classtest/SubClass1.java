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
public class SubClass1 extends SupperClass {
	public SubClass1(int a, String test) {
		super(a, test);
		// TODO Auto-generated constructor stub
	}

	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	public void print() {
		System.out.println("" + a + " " + test);
	}

	public void change() {
		a = 10;
		test = "test1-2";
	}
}
