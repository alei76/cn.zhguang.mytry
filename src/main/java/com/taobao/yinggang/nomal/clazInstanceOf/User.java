/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * User.java File Created at ����5:05:51
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
package com.taobao.yinggang.nomal.clazInstanceOf;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��9��24��
 */
public class User {
	private String name;

	private int age;

	private long height;

	private double weight;

	public User(String name, int age, long height, double weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	/**
	 * ��ȡname
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * ����name
	 * 
	 * @param name Ҫ���õ�name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ȡage
	 * 
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * ����age
	 * 
	 * @param age Ҫ���õ�age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * ��ȡheight
	 * 
	 * @return height
	 */
	public long getHeight() {
		return height;
	}

	/**
	 * ����height
	 * 
	 * @param height Ҫ���õ�height
	 */
	public void setHeight(long height) {
		this.height = height;
	}

	/**
	 * ��ȡweight
	 * 
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * ����weight
	 * 
	 * @param weight Ҫ���õ�weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
