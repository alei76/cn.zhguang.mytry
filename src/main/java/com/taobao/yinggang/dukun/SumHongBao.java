/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * SumHongBao.java File Created at 上午10:23:01
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
package com.taobao.yinggang.dukun;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 计算杜琨求和红包
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2015年2月7日
 */
public class SumHongBao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "D:\\tmp\\numbers\\numbers";
		System.out.println("Start...");
		//		int mayLineCount = 10*10000;
		//		int totalThreadNum = 5;
		//		int jiange = mayLineCount / totalThreadNum;
		//		for (int i = 0; i < totalThreadNum; i++) {
		//			int startLine = i * jiange;
		//			int endLine = (i + 1) * jiange;
		//			if (i == totalThreadNum - 1) {
		//				endLine = 0;
		//			}

		new ReadThread(0, 0, path).start();
		//		}
		System.out.println("End...");
	}

}

class ReadThread extends Thread {
	int startLine = 0;

	int endLine = 0;

	String filePath = "";

	int hongbao[] = { 0, 0, 0, 0, 0, 0, 0 };

	public ReadThread(int startLine, int endLine, String filePath) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.filePath = filePath;
	}

	private void sum(String line) {
		for (int i = 0; i < 7; i++) {
			hongbao[i] += line.charAt(i) - 48;
		}
	}

	public void run() {
		try {
			File file = new File(filePath);
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 10 * 1024 * 1024);
			String line = "";
			int count = 0;
			while ((line = reader.readLine()) != null) {
				sum(line);
				count++;
				if (count % 100000 == 0) {
					System.out.println("thread  processed " + count);
				}

			}
			System.out.println("\nthread processed: ");
			for (int i = 0; i < 7; i++) {
				System.out.print("," + hongbao[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
