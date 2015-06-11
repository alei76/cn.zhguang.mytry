/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SerializableTest.java File Created at 下午6:03:31
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
package com.taobao.yinggang.nomal.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.taobao.yinggang.nomal.serializable.pak1.User;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年8月27日
 */
public class SerializableTest {
	private static final String UTF_8 = "utf-8";

	public static void savePerson() {
		User user1 = new User("Jay", "111");
		try {
			FileOutputStream fos = new FileOutputStream("D:\\user1.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(user1);

			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void restorePerson() {
		try {
			FileInputStream fis = new FileInputStream("D:\\user1.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			com.taobao.yinggang.nomal.serializable.pak1.pak2.User user2 = (com.taobao.yinggang.nomal.serializable.pak1.pak2.User) ois.readObject();
			System.out.println("Name is: " + user2.getName());
			System.out.println("Age is: " + user2.getNick());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// savePerson();
		restorePerson();
	}
}
