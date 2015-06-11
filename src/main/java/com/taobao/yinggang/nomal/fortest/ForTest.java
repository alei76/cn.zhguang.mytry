/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * ForTest.java File Created at 上午9:46:39
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
package com.taobao.yinggang.nomal.fortest;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年7月3日
 */
public class ForTest {
	public static List<String> getList() {
		List<String> list = Lists.newArrayList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		System.out.println("getList");
		return list;
	}

	public static Set<String> getSet() {
		Set<String> set = Sets.newTreeSet();
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		return set;
	}

	/**
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		for (String str : getList()) {
			System.out.println(str);
		}

		// for (int i = 0; i < 3; i++) {
		// String str = getList().get(i);
		// System.out.println(str);
		// }

		// Set<String> sets = getSet();
		// for (String str : sets) {
		// sets.remove(str);
		// System.out.println(str);
		// }

		// Set<String> sets = getSet();
		// Iterator<String> it = sets.iterator();
		// while (it.hasNext()) {
		// String str = it.next();
		// it.remove();
		// // sets.remove(str);
		// System.out.println("remove:  " + str + ",set size=" + sets.size());
		// }
	}

}
