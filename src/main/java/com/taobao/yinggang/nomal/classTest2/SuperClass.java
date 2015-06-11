/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.classTest2;

/**
 * @author ggugzhg
 * @version $Revision: $
 */
public class SuperClass {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	private String value;

	public SuperClass() {
		value = "super";
	}

	public void print() {
		System.out.print(value);
	}
}
