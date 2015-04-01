/*
 * Copyright (c) 2011 by Daniel Zhang, all rights reserved.
 */

package com.taobao.yinggang.nomal.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 张广
 */
public class ProducerConsumer {
	private static List<Integer> intList = new ArrayList<Integer>();

	private static Thread main;

	private static Thread producer;

	private static Thread consumer;

	private static boolean isRunning = false;

	private static void startProducer() {
		producer = new Thread(new Runnable() {
			public void run() {
				while (isRunning) {
					int ran = new Random(System.nanoTime()).nextInt(100);

					if (intList.size() > 50) {
						try {
							System.err.println("+++List is full, wait...");
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						intList.add(ran);
						System.out.println("++Add an integer: " + ran);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		producer.setPriority(Thread.NORM_PRIORITY);
		producer.start();
	}

	private static void startConsumer() {
		consumer = new Thread(new Runnable() {
			public void run() {
				while (isRunning) {
					if (intList.size() <= 0) {
						try {
							System.err.println("---List is empty, wait...");
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						int ran = intList.remove(0);
						System.out.println("--Use an integer: " + ran);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		consumer.setPriority(Thread.NORM_PRIORITY);
		consumer.start();
	}

	public static void startMainThread() {
		main = new Thread(new Runnable() {
			public void run() {
				isRunning = true;
				startProducer();
				startConsumer();
			}
		});
		main.setPriority(Thread.MAX_PRIORITY);
		main.start();
	}

	public static void stopAllThread() {
		isRunning = false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProducerConsumer.startMainThread();
	}

}
