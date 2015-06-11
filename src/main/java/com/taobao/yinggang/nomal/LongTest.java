/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * LongTest.java File Created at 下午3:52:04
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
package com.taobao.yinggang.nomal;

/**
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2015年3月16日
 */
public class LongTest {

	/**
	 * @version 1.0
	 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
	 * @date 2015年3月16日 下午3:52:05
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//		Long aa = 345L;
		//		Long bb = new Long(345L);
		//
		//		System.out.println(aa == bb);
		//		System.out.println(aa.longValue() == bb.longValue());
		//		System.out.println(aa.equals(bb));
		//		System.out.println(aa.equals(bb.longValue()));
		//		System.out.println(bb == 345L);
		//		;

		Long value = 35184374186016L;
		System.out.println(Long.toBinaryString(value));

		Long newValue = value & 2097152L;
		System.out.println(Long.toBinaryString(newValue));
		System.out.println(newValue);

	}

}
