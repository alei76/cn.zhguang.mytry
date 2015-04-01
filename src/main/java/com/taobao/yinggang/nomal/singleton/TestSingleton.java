/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SingletonTest.java File Created at ����9:38:54
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
 * ����Singleton�Ŀɿ��ԡ�
 * 
 * @author ������(laozizhu.com)
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
			// ����Singletom1
			// �õ���һ��ʵ��
			TestSingleton1 s1 = TestSingleton1.getInstance();
			// �����õ��ڶ���ʵ��
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
			// ����Singletom1
			// �õ���һ��ʵ��
			TestSingleton2 s1 = TestSingleton2.getInstance();
			// �����õ��ڶ���ʵ��
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
			// ����Singletom1
			// �õ���һ��ʵ��
			TestSingleton3 s1 = TestSingleton3.getInstance();
			// �����õ��ڶ���ʵ��
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
			// ����Singletom1
			// �õ���һ��ʵ��
			InnerClassSingleton s1 = InnerClassSingleton.getInstance();
			// �����õ��ڶ���ʵ��
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
 * һ����ͨ��Singletoneʵ�֡�
 * 
 * @author ������(laozizhu.com)
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
 * һ�����쳣ǿ���˵�Singletoneʵ�֡�
 * 
 * @author ������(laozizhu.com)
 */
class TestSingleton2 {
	private static final TestSingleton2 INSTANCE = new TestSingleton2();

	public static TestSingleton2 getInstance() {
		return INSTANCE;
	}

	private static boolean initSign;

	private TestSingleton2() {
		if (initSign) {
			throw new RuntimeException("ʵ��ֻ�ܽ���һ��");
		}
		initSign = true;
	}
}

/**
 * ö��ʵ�ֵ�Singleton
 * 
 * @author ������(laozizhu.com)
 */
enum TestSingleton3 {
	INSTANCE;
	public static TestSingleton3 getInstance() {
		return INSTANCE;
	}

}