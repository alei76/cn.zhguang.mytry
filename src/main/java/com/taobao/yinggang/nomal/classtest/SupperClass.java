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
public abstract class SupperClass {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	protected int a;

	protected String test;

	public abstract void print();

	public SupperClass(int a, String test) {
		this.a = a;
		this.test = test;
	}

	public abstract void change();

	protected void changeTest() {
		test = "test super";
	}

	protected static void staticTest() {

	}

	protected void setNull() {
		test = null;
	}
}
