/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * User.java File Created at 下午5:05:51
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
 * @since 2014年9月24日
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
	 * 获取name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name 要设置的name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取age
	 * 
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 设置age
	 * 
	 * @param age 要设置的age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 获取height
	 * 
	 * @return height
	 */
	public long getHeight() {
		return height;
	}

	/**
	 * 设置height
	 * 
	 * @param height 要设置的height
	 */
	public void setHeight(long height) {
		this.height = height;
	}

	/**
	 * 获取weight
	 * 
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * 设置weight
	 * 
	 * @param weight 要设置的weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
