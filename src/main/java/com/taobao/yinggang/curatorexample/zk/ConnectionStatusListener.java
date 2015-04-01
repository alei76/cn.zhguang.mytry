/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ConnectionStatusListener.java File Created at 下午3:25:41
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

import org.apache.curator.framework.state.ConnectionState;

/**
 * 连接状态监听器
 * 
 * @From 来源于应用jinwei-common，有改动，原作者：qihao
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public interface ConnectionStatusListener {
	/**
	 * Called when the zookeeper connection state has changed.
	 * 
	 * @param state The new state.
	 * @throws Exception On any error.
	 */
	public void handleStateChanged(ConnectionState state) throws Exception;
}
