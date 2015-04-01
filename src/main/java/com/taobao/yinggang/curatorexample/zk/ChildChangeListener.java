/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ChildChangeListener.java File Created at ����3:25:41
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
 * �ӽڵ����ļ������������ӽڵ�����ӡ�ɾ�������������ݵı仯
 * 
 * @From ��Դ��Ӧ��jinwei-common���иĶ���ԭ���ߣ�qihao
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��6��4��
 */
public interface ChildChangeListener {

	/**
	 * @version 1.0
	 * @param parentPath ���ڵ�·��
	 * @param childPath �仯���ӽڵ�·����ȫ·����
	 * @param type
	 */
	public void handleChild(String parentPath, String childPath, PathChildrenCacheEvent.Type type);
}
