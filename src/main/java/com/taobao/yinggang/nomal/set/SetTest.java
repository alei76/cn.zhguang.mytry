/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SetTest.java File Created at 下午9:26:58
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
package com.taobao.yinggang.nomal.set;

import java.util.Date;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年12月19日
 */
public class SetTest {
	public static void main(String[] args) {
		User user1 = new User("AAA", 15, new Date());
		User user2 = new User("BBB", 20, new Date());
		Set<User> userSet = Sets.newHashSet();
		for (int i = 0; i < 20; i++) {
			if (i < 10) {
				userSet.add(user1);
			} else {
				userSet.add(user2);
			}
		}
		System.out.println(userSet.size());
		for (User user : userSet) {
			System.out.println(user.toString());
		}
	}
}
