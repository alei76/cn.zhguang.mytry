/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * AlterableScheduleTimer.java File Created at 下午10:38:31
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
package com.taobao.yinggang.nomal.timer.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持并发的可变频线程调度器
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月17日
 */
public class AlterableScheduleTimer {
	private static final Logger logger = LoggerFactory.getLogger(AlterableScheduleTimer.class);

	private ScheduledExecutorService scheduleExecutor;

	/**
	 * 线程池大小
	 */
	private int poolSize;

	/**
	 * 设置线程池大小，不设置或设置为0或1，则使用单线程池
	 * 
	 * @version 1.0
	 * @param poolSize
	 */
	public void setPoolSize(Integer poolSize) {
		this.poolSize = poolSize == null ? 0 : poolSize;
	}

	/**
	 * 初始化
	 * 
	 * @version 1.0
	 */
	public void init() {
		initPool();
		logger.info("alterableTimerInit$Alterable schedule timer inited...");
	}

	/**
	 * 初始化线程池大小
	 * 
	 * @version 1.0
	 */
	private void initPool() {
		if (poolSize == 0 || poolSize == 1) {
			scheduleExecutor = Executors.newSingleThreadScheduledExecutor();
		} else {
			scheduleExecutor = Executors.newScheduledThreadPool(poolSize);
		}
	}

	/**
	 * 销毁timer
	 * 
	 * @version 1.0
	 */
	public void destory() {
		scheduleExecutor.shutdown();
		logger.info("alterableTimerDestory$Alterable schedule timer destoried...");
	}

	/**
	 * 按固定的时间延迟开始执行调度，每次执行完，延迟固定时间
	 * 
	 * @version 1.0
	 * @param task 任务
	 * @param initDelay 起始延迟时间
	 * @param delay 每次执行完一次调度之后延迟时间
	 * @param timeUnit 时间单位
	 */
	public void startFixDelay(Runnable task, long initDelay, long delay, TimeUnit timeUnit) {
		scheduleExecutor.scheduleWithFixedDelay(task, initDelay, delay, timeUnit);
		logger.info("alterableTimerDelayStart$Alterable schedule timer start task {} at a fix delay, initDelay={},delay={},timeUtil={}",
				new Object[] { task.toString(), initDelay, delay, timeUnit.name() });
	}

	/**
	 * 按固定的频率开始执行调度，每次开始执行之间的间隔相同
	 * 
	 * @version 1.0
	 * @param task 任务
	 * @param initDelay 起始执行时间
	 * @param period 每次执行完一次调度之后
	 * @param timeUnit
	 */
	public void startFixRate(Runnable task, long initDelay, long period, TimeUnit timeUnit) {
		scheduleExecutor.scheduleAtFixedRate(task, initDelay, period, timeUnit);
		logger.info("alterableTimerRateStart$Alterable schedule timer start task {} at a fix rate, initDelay={},period={},timeUtil={}",
				new Object[] { task.toString(), initDelay, period, timeUnit.name() });
	}

	/**
	 * 等待最后一次调度执行完成后停止
	 * 
	 * @version 1.0
	 */
	public void shutdown() {
		scheduleExecutor.shutdown();
		logger.info("alterableTimerShutdown$Alterable schedule timer shutdown");
	}

	/**
	 * 立即停止
	 * 
	 * @version 1.0
	 */
	public void shutdownNow() {
		scheduleExecutor.shutdownNow();
	}

	/**
	 * 等待
	 * 
	 * @version 1.0
	 * @param timeout
	 * @param unit
	 * @return
	 * @throws InterruptedException
	 */
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return scheduleExecutor.awaitTermination(timeout, unit);
	}

	/**
	 * 按固定时延重启
	 * 
	 * @version 1.0
	 * @param task
	 * @param initDelay
	 * @param delay
	 * @param timeout 超时时间，单位为timeUnit，超过这个时间原线程组仍然无法关闭，则认为启动失败
	 * @param timeUnit
	 * @return
	 * @throws InterruptedException
	 */
	public boolean restartFixDelay(Runnable task, long initDelay, long delay, long timeout, TimeUnit timeUnit) throws InterruptedException {
		scheduleExecutor.shutdown();
		logger.info("alterableTimerDelayRestart$Alterable schedule timer will restart start task {} at a fix delay, initDelay={},delay={},timeUtil={}",
				new Object[] { task.toString(), initDelay, delay, timeUnit.name() });
		if (scheduleExecutor.awaitTermination(timeout, TimeUnit.SECONDS)) {
			initPool();
			scheduleExecutor.scheduleWithFixedDelay(task, initDelay, delay, timeUnit);
			logger.info("alterableTimerDelayRestart$Alterable schedule timer restart task {} at a fix delay, initDelay={},delay={},timeUtil={}", new Object[] {
					task.toString(), initDelay, delay, timeUnit.name() });
			return true;
		}
		return false;
	}

	/**
	 * 按固定时延重启，一直等待，每等待1s，打一条日志
	 * 
	 * @version 1.0
	 * @param task
	 * @param initDelay
	 * @param delay
	 * @param timeUnit
	 * @throws InterruptedException
	 */
	public void restartFixDelay(Runnable task, long initDelay, long delay, TimeUnit timeUnit) throws InterruptedException {
		scheduleExecutor.shutdown();
		logger.info("alterableTimerDelayRestart$Alterable schedule timer will restart task {} at a fix delay, initDelay={},delay={},timeUtil={}", new Object[] {
				task.toString(), initDelay, delay, timeUnit.name() });
		int seconds = 0;
		while (!scheduleExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
			seconds++;
			logger.info("alterableTimerDelayRestartWait$Alterable schedule timer wait for old delay timer shutdown...time={} s", seconds);
		}
		initPool();
		scheduleExecutor.scheduleWithFixedDelay(task, initDelay, delay, timeUnit);
		logger.info("alterableTimerDelayRestart$Alterable schedule timer restart task {} at a fix delay, initDelay={},delay={},timeUtil={}", new Object[] {
				task.toString(), initDelay, delay, timeUnit.name() });
	}

	/**
	 * 按固定频率重启
	 * 
	 * @version 1.0
	 * @param task
	 * @param initDelay
	 * @param period
	 * @param timeout 超时时间，单位为timeUnit，超过这个时间原线程组仍然无法关闭，则认为启动失败
	 * @param timeUnit
	 * @return
	 * @throws InterruptedException
	 */
	public boolean restartFixRate(Runnable task, long initDelay, long period, long timeout, TimeUnit timeUnit) throws InterruptedException {
		scheduleExecutor.shutdown();
		logger.info("alterableTimerRateRestart$Alterable schedule timer will restart start task {} at a fix rate, initDelay={},delay={},timeUtil={}",
				new Object[] { task.toString(), initDelay, period, timeUnit.name() });
		if (scheduleExecutor.awaitTermination(timeout, TimeUnit.SECONDS)) {
			initPool();
			scheduleExecutor.scheduleAtFixedRate(task, initDelay, period, timeUnit);
			logger.info("alterableTimerRateRestart$Alterable schedule timer restart task {} at a fix rate, initDelay={},delay={},timeUtil={}", new Object[] {
					task.toString(), initDelay, period, timeUnit.name() });
			return true;
		}
		return false;
	}

	/**
	 * 按固定时延重启，一直等待，每等待1s，打一条日志
	 * 
	 * @version 1.0
	 * @param task
	 * @param initDelay
	 * @param period
	 * @param timeUnit
	 * @throws InterruptedException
	 */
	public void restartFixRate(Runnable task, long initDelay, long period, TimeUnit timeUnit) throws InterruptedException {
		scheduleExecutor.shutdown();
		logger.info("alterableTimerRateRestart$Alterable schedule timer will restart start task {} at a fix rate, initDelay={},delay={},timeUtil={}",
				new Object[] { task.toString(), initDelay, period, timeUnit.name() });
		int seconds = 0;
		while (!scheduleExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
			seconds++;
			logger.info("alterableTimerRateRestartWait$Alterable schedule timer wait for old rate timer shutdown...time={} s", seconds);
		}
		initPool();
		scheduleExecutor.scheduleAtFixedRate(task, initDelay, period, timeUnit);
		logger.info("alterableTimerRateRestart$Alterable schedule timer restart task {} at a fix rate, initDelay={},delay={},timeUtil={}",
				new Object[] { task.toString(), initDelay, period, timeUnit.name() });
	}

	public static void main(String[] args) {
		AlterableScheduleTimer timer = new AlterableScheduleTimer();
		timer.setPoolSize(1);
		timer.init();

		MyScheduledTask task = new MyScheduledTask("t3");
		Thread thread = new Thread(task, "TestThread");
		// for (int i = 0; i < 3; i++) {
		// singleThread.schedule(task, 1, TimeUnit.SECONDS);
		try {
			timer.restartFixDelay(thread, 1, 1000, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// // }
		// try {
		// TimeUnit.SECONDS.sleep(9);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println("Change");
		//
		// try {
		// System.out.println("开始关闭线程组：" + System.currentTimeMillis());
		// timer.restartFixDelay(thread, 1, 5, Integer.MAX_VALUE, TimeUnit.SECONDS);
		// System.out.println("已关闭重新打开：" + System.currentTimeMillis());
		//
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// try {
		// TimeUnit.SECONDS.sleep(9);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println("Change");
		//
		// try {
		// System.out.println("开始关闭线程组：" + System.currentTimeMillis());
		// timer.restartFixDelay(thread, 1, 3, Integer.MAX_VALUE, TimeUnit.SECONDS);
		// System.out.println("已关闭重新打开：" + System.currentTimeMillis());
		//
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}
}
