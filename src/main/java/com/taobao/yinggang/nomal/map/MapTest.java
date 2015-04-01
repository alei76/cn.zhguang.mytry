/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * MapTest.java File Created at 下午7:31:58
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
package com.taobao.yinggang.nomal.map;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月23日
 */
public class MapTest {

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, User> userMap = new HashMap<String, User>();

			User user1 = new User("testa", 5, sdf.parse("2014-00-00 00:00:00"));

			User user2 = new User("testb", 6, sdf.parse("2014-01-01 01:00:00"));

			userMap.put("aaa", user1);
			userMap.put("bbb", user1);
			userMap.put("ccc", user1);
			userMap.put("ddd", user1);
			userMap.put("eee", user2);
			MapUtil.print(userMap);

			User user3 = userMap.get("aaa");
			user3.setName("testc");
			user3.setAge(7);
			user3.setDate(sdf.parse("2014-02-02 02:00:00"));
			MapUtil.print(userMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
