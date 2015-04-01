/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * RunnableAndCallable2Future.java File Created at ����4:53:37
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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 
 * ͨ���򵥵Ĳ��Գ���������Runnable��Callableͨ��Executor�����ȵ�ʱ����Future�Ĺ�ϵ 
 */
public class RunnableAndCallable2Future {

	public static void main(String[] args) {

		// ����һ��ִ������ķ���  
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try {
			//1.Runnableͨ��Future���ؽ��Ϊ��  
			//����һ��Runnable�������ȣ��ȴ�����ִ����ϣ�ȡ�÷��ؽ��  
			Future<?> runnable1 = executor.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println("runnable1 running.");
				}
			});
			System.out.println("Runnable1:" + runnable1.get());

			// 2.Callableͨ��Future�ܷ��ؽ��  
			//�ύ��ִ��������������ʱ������һ�� Future����  
			// �����õ�����ִ�еĽ���������쳣�ɶ����Future������в���  
			Future<String> future1 = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub  
					return "result=task1";
				}
			});
			// �������Ľ�����������get��������ǰ�̻߳�ȴ�����ִ����Ϻ������ִ��  
			System.out.println("task1: " + future1.get());

			//3. ��Callable����cancel���ԶԶԸ���������ж�  
			//�ύ��ִ��������������ʱ������һ�� Future����  
			// �����õ�����ִ�еĽ���������쳣�ɶ����Future������в���  
			Future<String> future2 = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					try {
						while (true) {
							System.out.println("task2 running.");
							Thread.sleep(50);
						}
					} catch (InterruptedException e) {
						System.out.println("Interrupted task2.");
					}
					return "task2=false";
				}
			});

			// �ȴ�5�����ֹͣ�ڶ���������Ϊ�ڶ���������е�������ѭ��  
			Thread.sleep(10);
			System.out.println("task2 cancel: " + future2.cancel(true));

			// 4.��Callableʱ�׳��쳣��FutureʲôҲȡ������  
			// ��ȡ������������������Ϊִ�е���������������쳣  
			// �����������佫�����쳣���׳�  
			Future<String> future3 = executor.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					throw new Exception("task3 throw exception!");
				}

			});
			System.out.println("task3: " + future3.get());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// ֹͣ����ִ�з���  
		executor.shutdownNow();
	}
}
