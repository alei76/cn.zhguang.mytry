/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SyncThread.java File Created at 下午1:23:57
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
package com.taobao.yinggang.nomal.concurrent;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年5月24日
 */
public class SyncThread implements Runnable {
	private TestNode testNode;

	private int flag = 0;

	private String threadName;

	public SyncThread(TestNode testNode, int flag, String threadName) {
		this.testNode = testNode;
		this.flag = flag;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		System.out.println("Thread runs:" + threadName + ",flag:" + flag);
		if (flag == 0) {
			testNode.printA("aaa");
		} else if (flag == 1) {
			testNode.printB("bbb");
		} else {
			testNode.printC("ccc");
		}
	}

	/**
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		TestNode testNode = new TestNode();
		Thread thread1 = new Thread(new SyncThread(testNode, 0, "t1"));
		thread1.start();
		Thread thread2 = new Thread(new SyncThread(testNode, 1, "t2"));
		thread2.start();
		Thread thread3 = new Thread(new SyncThread(testNode, 1, "t3"));
		thread3.start();
		Thread thread4 = new Thread(new SyncThread(testNode, 2, "t4"));
		thread4.start();
		Thread thread5 = new Thread(new SyncThread(testNode, 2, "t5"));
		thread5.start();
		Thread thread6 = new Thread(new SyncThread(testNode, 2, "t6"));
		thread6.start();
	}

}
