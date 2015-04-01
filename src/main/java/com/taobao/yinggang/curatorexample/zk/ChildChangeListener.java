/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ChildChangeListener.java File Created at 下午3:25:41
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

import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;

/**
 * 子节点变更的监听器，监听子节点的增加、删除，不监听数据的变化
 * 
 * @From 来源于应用jinwei-common，有改动，原作者：qihao
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public interface ChildChangeListener {

	/**
	 * @version 1.0
	 * @param parentPath 父节点路径
	 * @param childPath 变化的子节点路径（全路径）
	 * @param type
	 */
	public void handleChild(String parentPath, String childPath, PathChildrenCacheEvent.Type type);
}
