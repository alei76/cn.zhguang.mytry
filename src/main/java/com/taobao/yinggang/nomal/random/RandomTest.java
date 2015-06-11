/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: Try
 * 
 * RandomTest.java File Created at 上午10:53:53
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
package com.taobao.yinggang.nomal.random;

import java.util.Random;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年5月19日
 */
public class RandomTest {
	public static void main(String[] args) {
		//		Random random = new Random(System.currentTimeMillis());
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			int a = new Random().nextInt(30000);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			int a = new Random().nextInt(300);
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
