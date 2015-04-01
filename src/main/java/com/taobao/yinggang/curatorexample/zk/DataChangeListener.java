/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * DataChangeListener.java File Created at 下午3:25:41
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
package com.taobao.yinggang.curatorexample.zk;

/**
 * 数据变化监听器
 * 
 * @From 来源于应用jinwei-common，有改动，原作者：qihao
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public interface DataChangeListener {
	/**
	 * 数据变化回调方法
	 * 
	 * @param path 指定zk的path
	 * @param data 变化的数据，如果数据被删除则为null
	 */
	public void handleData(String path, String data);
}