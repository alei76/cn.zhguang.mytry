/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: Try
 * 
 * TimerTry.java File Created at ����2:07:03
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
package com.taobao.yinggang.nomal.timer;

import java.io.IOException;
import java.util.Timer;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��3��20��
 */
public class TimerTry {

	public static void main(String[] args) {
		Timer timer = new Timer();
		MyTask task = new MyTask();
		timer.schedule(task, 1000, 2000);// ��1���ִ�д�����,ÿ�μ��2��,�������һ��Data����,�Ϳ�����ĳ���̶���ʱ��ִ���������.
		while (true) {// ���������ֹͣ�������,�����һֱѭ��ִ�д�������
			try {
				int ch = System.in.read();
				if (ch - 'c' == 0) {
					timer.cancel();// ʹ����������˳�����
				} else if (ch - 'r' == 0) {
					timer = restart(timer, task, 1000, 1000);
				} else if (ch - 'f' == 0) {
					timer = restart(timer, task, 1000, 3000);
				} else if (ch == 'q') {
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class MyTask extends java.util.TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("________");
		}
	}

	public static Timer restart(Timer oldTimer, MyTask task, long delay, long period) {
		if (oldTimer != null) {
			task.cancel();
			oldTimer.cancel();
			System.out.println("old timer canceled!");
		} else {
			System.out.println("old timer == null");
		}
		Timer newTimer = new Timer("a");
		newTimer.schedule(task, delay, period);
		return newTimer;
	}
}
