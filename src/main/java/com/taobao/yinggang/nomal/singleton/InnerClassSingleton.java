/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * InnerClassSingleton.java File Created at ����9:42:46
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


/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��10��16��
 */
public class InnerClassSingleton {
	/**
	 * �����ڲ���ֻ��ʼ��һ�ε���������singleton����ֹ���߳���ɵĲ�ͬ��
	 */
	private static class Singleton {
		static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
	}

	/**
	 * ˽�л����췽��
	 */
	private InnerClassSingleton() {
	}

	/**
	 * ��ù���ʵ��
	 * 
	 * @return
	 */
	public static InnerClassSingleton getInstance() {
		return Singleton.INSTANCE;
	}
}
