/*
 * Copyright (c) 2011 by Daniel Zhang, all rights reserved.
 */

package com.taobao.yinggang.nomal.accesscontrol;

/**
 * @author 张广
 */
public class Class1 {
	public void fun() {
		Class2 class2 = new Class2();
		class2.funDefault();
		class2.funProtected();
		class2.funPublic();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
