/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * DataChangeListener.java File Created at ����3:25:41
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
 * ���ݱ仯������
 * 
 * @From ��Դ��Ӧ��jinwei-common���иĶ���ԭ���ߣ�qihao
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��6��4��
 */
public interface DataChangeListener {
	/**
	 * ���ݱ仯�ص�����
	 * 
	 * @param path ָ��zk��path
	 * @param data �仯�����ݣ�������ݱ�ɾ����Ϊnull
	 */
	public void handleData(String path, String data);
}