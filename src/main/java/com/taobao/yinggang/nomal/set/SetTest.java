/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SetTest.java File Created at ����9:26:58
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

import java.util.Set;

import com.google.common.collect.Sets;
import com.taobao.util.CollectionUtil;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��12��19��
 */
public class SetTest {
	public static void main(String[] args) {
		//		User user1 = new User("AAA", 15, new Date());
		//		User user2 = new User("BBB", 20, new Date());
		//		Set<User> userSet = Sets.newHashSet();
		//		for (int i = 0; i < 20; i++) {
		//			if (i < 10) {
		//				userSet.add(user1);
		//			} else {
		//				userSet.add(user2);
		//			}
		//		}
		//		System.out.println(userSet.size());
		//		for (User user : userSet) {
		//			System.out.println(user.toString());
		//		}

		Set<String> sets = Sets.newCopyOnWriteArraySet();
		sets.add("aa");
		sets.add("bb");
		sets.add("cc");
		System.out.println(CollectionUtil.collection2String(sets, ","));
		System.out.println(sets.contains(new String("aa")));
		sets.remove(new String("dd"));
		sets.remove(new String("aa"));
		sets.add(new String("ee"));
		sets.add(new String("ee"));
		sets.add(new String("ee"));
		sets.add(new String("ee"));

		System.out.println(sets);
		System.out.println(sets.contains(new String("aa")));
	}
}
