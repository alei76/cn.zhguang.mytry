/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * TestNode.java File Created at 下午1:21:39
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
package com.taobao.yinggang.nomal.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年5月24日
 */
public class TestNode {
	public synchronized void printA(String str) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("printA" + str);
	}

	public synchronized void printB(String str) {
		System.out.println("printB" + str);

	}

	public void printC(String str) {
		System.out.println("before printC... " + str);
		synchronized (this) {
			System.out.println("printC" + str);
		}
		System.out.println("after printC" + str);
	}

}
