/*
 * Copyright (c) 2011 by Daniel Zhang, all rights reserved.
 */

package com.taobao.yinggang.nomal.enums;

/**
 * @author 张广
 */
public enum TestEnum {
	AAA(1), BBB(2);
	private int num;

	TestEnum(int num) {
		this.setNum(num);
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public static void main(String[] args) {
		TestEnum enum1 = TestEnum.valueOf("AAA");
		TestEnum enum2 = TestEnum.valueOf("BBB");
		TestEnum enum3 = TestEnum.valueOf("AAA");
		System.out.println(enum1.equals(enum2));
		System.err.println(enum1 == enum3);
	}
}
