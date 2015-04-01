package com.taobao.yinggang.nomal.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new MyThread();
		System.out.println("State1:" + thread1.getState());
		thread1.start();
		System.out.println("State2:" + thread1.getState());

		thread1.sleep(5000L);
		System.out.println("State3:" + thread1.getState());

		TimeUnit.SECONDS.sleep(5L);
		System.out.println("State4:" + thread1.getState());

		thread1.wait();
		System.out.println("State5:" + thread1.getState());

		TimeUnit.SECONDS.sleep(5L);
		System.out.println("State6:" + thread1.getState());

		Thread.currentThread().notify();
		System.out.println("State7:" + thread1.getState());

		thread1.stop();
		System.out.println("State8:" + thread1.getState());

		TimeUnit.SECONDS.sleep(5L);
		System.out.println("State9:" + thread1.getState());

		try {
			//检查程序是否发生中断  
			while (!Thread.interrupted()) {
				System.out.println("I am running!");
				//point1 before sleep  
				Thread.sleep(20);
				//point2 after sleep  
				System.out.println("Calculating");
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting by Exception");
		}
		System.out.println("ATask.run() interrupted!");

		Runnable oneRunnable = new SomeRunnable();
		Thread oneThread = new Thread(oneRunnable);
		oneThread.start();

		Callable<String> oneCallable = new SomeCallable();
		FutureTask<String> oneTask = new FutureTask<String>(oneCallable);
		Thread twoThread = new Thread(oneTask);
		oneThread.start();
	}

}

class SomeRunnable implements Runnable
{
	public void run()
	{
		//do something here
	}
}

class SomeCallable implements Callable<String>
{
	public String call() throws Exception {
		// do something
		return "";
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println("State10:" + this.getState());
		for (int i = 0; i < 1000; i++) {
			System.out.println("MyThread - " + i + " - " + System.currentTimeMillis());
			try {
				Thread.currentThread().sleep(500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
