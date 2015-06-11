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
public class ClassTest {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SupperClass t1 = new SubClass1(5, "test1");
		t1.print();
		SupperClass t2 = new SubClass2(6, "test2");
		t2.print();
		t1.print();
		t2.print();

		t1.change();
		t1.print();
		t2.print();

		t2.change();
		t1.print();
		t2.print();

		t1.changeTest();
		t1.print();
		t2.print();

		t2.setNull();
		t1.print();
		t2.print();

	}
}
