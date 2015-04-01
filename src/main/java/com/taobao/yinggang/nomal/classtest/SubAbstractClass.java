/*
 * Copyright (c) 2011 by Daniel Zhang, all rights reserved.
 */

package com.taobao.yinggang.nomal.classtest;

/**
 * @author 张广
 */
public abstract class SubAbstractClass extends SupperClass {

	public SubAbstractClass(int a, String test) {
		super(a, test);
		// TODO Auto-generated constructor stub
	}

	public abstract void fun();

	@Override
	public void change() {
		// TODO Auto-generated method stub

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	//
	// @Override
	// public static void staticTest() {
	//
	// }

	// public abstract synchronized void fun2();

}
