/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * InnerClassSingleton.java File Created at 上午9:42:46
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
 * @since 2014年10月16日
 */
public class InnerClassSingleton {
	/**
	 * 利用内部类只初始化一次的特性来做singleton，防止多线程造成的不同步
	 */
	private static class Singleton {
		static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
	}

	/**
	 * 私有化构造方法
	 */
	private InnerClassSingleton() {
	}

	/**
	 * 获得工厂实例
	 * 
	 * @return
	 */
	public static InnerClassSingleton getInstance() {
		return Singleton.INSTANCE;
	}
}
