package com.taobao.yinggang.nomal.myJava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hehe = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		System.out.print(hehe);
		Random ram = new Random();
		for (int i = 0; i < 100; i++) {
			System.out.print(" " + ram.nextInt(200));
		}
		String a = "52345";
		a = a.length() < 5 ? a.substring(0, a.length()) : a.substring(0, 4);
		System.out.println();
		System.out.println(a);
	}

}
