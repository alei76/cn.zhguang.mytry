/*
 * Copyright (c) 2011 by Daniel Zhang, all rights reserved.
 */

package com.taobao.yinggang.nomal.trycatch;

/**
 * @author 张广
 */
public class TryCatchFinally {
	@SuppressWarnings("finally")
	public static int fun() {
		int t;
		t = 3;
		try {
			if (t < 5)
				throw new Exception("test");
			return 1;
		} catch (Exception e) {
			return 0;
		} finally {
			return 2;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(TryCatchFinally.fun());
	}
}
