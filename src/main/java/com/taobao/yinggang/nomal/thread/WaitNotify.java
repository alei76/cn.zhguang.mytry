/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * WaitNotify.java File Created at 下午4:16:58
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
package com.taobao.yinggang.nomal.thread;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2015年1月31日
 */
class MyObject implements Runnable {
	private Monitor monitor;

	public MyObject(Monitor monitor) {
		this.monitor = monitor;
	}

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("i'm going.");
			monitor.gotMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Monitor implements Runnable {
	private volatile boolean go = false;

	public synchronized void gotMessage() throws InterruptedException {
		go = true;
		notify();
	}

	public synchronized void watching() throws InterruptedException {
		while (go == false)
			wait();
		System.out.println("He has gone.");
	}

	public void run() {
		try {
			watching();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class WaitNotify {
	public static void main(String[] args) {
		System.out.println("Start...");
		Monitor monitor = new Monitor();
		MyObject object = new MyObject(monitor);
		new Thread(monitor).start();
		new Thread(object).start();
	}
}
