/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SortList.java File Created at ����11:44:49
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
package com.taobao.yinggang.nomal.list;

import java.util.List;

import com.google.common.collect.Lists;
import com.taobao.util.CollectionUtil;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��6��10��
 */
public class ListTest {

	/**
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		List<Long> list = Lists.newArrayList();
		list.add(111L);
		list.add(222L);
		list.add(333L);
		list.add(444L);
		System.out.println(CollectionUtil.collection2String(list, ","));
		System.out.println(String.valueOf(list));
		//		List<String> list = Lists.newArrayList();
		//		System.out.println(CollectionUtil.collection2String(list, ","));
	}
}
