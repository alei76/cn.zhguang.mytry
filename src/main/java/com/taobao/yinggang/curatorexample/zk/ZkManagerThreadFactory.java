/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ZkManagerThreadFactory.java File Created at ����2:13:10
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

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.common.lang.StringUtil;

/**
 * ����ZkManager�̳߳صĹ���
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��6��4��
 */
public class ZkManagerThreadFactory implements ThreadFactory {

	private final AtomicInteger threadNumber = new AtomicInteger(1);

	private final String namePrefix;

	public ZkManagerThreadFactory(String prefix, AtomicInteger poolNumber) {
		if (StringUtil.isBlank(prefix)) {
			this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
		} else {
			this.namePrefix = prefix + "-" + poolNumber.getAndIncrement() + "-thread-";
		}
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, this.namePrefix + this.threadNumber.getAndIncrement());
	}
}
