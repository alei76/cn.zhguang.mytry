/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SingletonTest.java File Created at 上午9:38:54
 * 
 * 
 * Copyright 2014 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.taobao.yinggang.nomal.singleton;

import java.lang.reflect.Constructor;

/**
 * 测试Singleton的可靠性。
 * 
 * @author 老紫竹(laozizhu.com)
 */
public class TestSingleton {
	public static void main(String[] args) {

		System.out.println("1-----------------------------------------------------------");
		testSingleton1();
		System.out.println("2-----------------------------------------------------------");
		testSingleton2();
		System.out.println("3-----------------------------------------------------------");
		testSingleton3();
		System.out.println("4-----------------------------------------------------------");
		testSingleton4();
	}

	public static void testSingleton1() {
		try {
			// 测试Singletom1
			// 拿到第一个实例
			TestSingleton1 s1 = TestSingleton1.getInstance();
			// 测试拿到第二个实例
			Class c1 = Class.forName("com.taobao.yinggang.nomal.singleton.TestSingleton1");
			Constructor[] cons = c1.getDeclaredConstructors();
			Constructor cc1 = cons[0];
			cc1.setAccessible(true);
			TestSingleton1 s2 = (TestSingleton1) cc1.newInstance(null);
			System.out.println(s1 + "/" + s2);
			System.out.println(s1 == s2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void testSingleton2() {
		try {
			// 测试Singletom1
			// 拿到第一个实例
			TestSingleton2 s1 = TestSingleton2.getInstance();
			// 测试拿到第二个实例
			Class c1 = Class.forName("com.taobao.yinggang.nomal.singleton.TestSingleton2");
			Constructor[] cons = c1.getDeclaredConstructors();
			Constructor cc1 = cons[0];
			cc1.setAccessible(true);
			TestSingleton2 s2 = (TestSingleton2) cc1.newInstance(null);
			System.out.println(s1 + "/" + s2);
			System.out.println(s1 == s2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void testSingleton3() {
		try {
			// 测试Singletom1
			// 拿到第一个实例
			TestSingleton3 s1 = TestSingleton3.getInstance();
			// 测试拿到第二个实例
			Class c1 = Class.forName("com.taobao.yinggang.nomal.singleton.TestSingleton3");
			Constructor[] cons = c1.getDeclaredConstructors();
			Constructor cc1 = cons[0];
			cc1.setAccessible(true);
			TestSingleton3 s2 = (TestSingleton3) cc1.newInstance(null);
			System.out.println(s1 + "/" + s2);
			System.out.println(s1 == s2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void testSingleton4() {
		try {
			// 测试Singletom1
			// 拿到第一个实例
			InnerClassSingleton s1 = InnerClassSingleton.getInstance();
			// 测试拿到第二个实例
			Class c1 = Class.forName("com.taobao.yinggang.nomal.singleton.InnerClassSingleton");
			Constructor[] cons = c1.getDeclaredConstructors();
			Constructor cc1 = cons[0];
			cc1.setAccessible(true);
			InnerClassSingleton s2 = (InnerClassSingleton) cc1.newInstance(null);
			System.out.println(s1 + "/" + s2);
			System.out.println(s1 == s2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

/**
 * 一个普通的Singletone实现。
 * 
 * @author 老紫竹(laozizhu.com)
 */
class TestSingleton1 {
	private static final TestSingleton1 INSTANCE = new TestSingleton1();

	public static TestSingleton1 getInstance() {
		return INSTANCE;
	}

	private TestSingleton1() {
	}
}

/**
 * 一个用异常强化了的Singletone实现。
 * 
 * @author 老紫竹(laozizhu.com)
 */
class TestSingleton2 {
	private static final TestSingleton2 INSTANCE = new TestSingleton2();

	public static TestSingleton2 getInstance() {
		return INSTANCE;
	}

	private static boolean initSign;

	private TestSingleton2() {
		if (initSign) {
			throw new RuntimeException("实例只能建造一次");
		}
		initSign = true;
	}
}

/**
 * 枚举实现的Singleton
 * 
 * @author 老紫竹(laozizhu.com)
 */
enum TestSingleton3 {
	INSTANCE;
	public static TestSingleton3 getInstance() {
		return INSTANCE;
	}

}