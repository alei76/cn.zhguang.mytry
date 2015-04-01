/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * APGenerate.java File Created at ����6:16:54
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
package com.taobao.yinggang.batchforwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��6��6��
 */
public class APGenerate {
	public static String generateApNum(String alipayUserId) {
		if (alipayUserId == null) {
			return null;
		}
		String alipayId = alipayUserId.toString();
		StringBuilder sb = new StringBuilder();
		sb.append(alipayId.substring(5, 6));
		sb.append(alipayId.substring(4, 5));
		sb.append(alipayId.substring(7, 15));

		long aliNo = Long.valueOf(sb.toString()) + 17576 - 1;// ����26^(4-1)-1��ȷ�����ܹ�����4λ

		String apNum = "";
		while (aliNo > 0) {
			long t = aliNo % 26;
			if (t == 0) {
				t = 26;
			}
			apNum = (char) (t + 64) + apNum;
			aliNo = aliNo / 26;
		}

		apNum = "AP" + apNum;
		return apNum;
	}

	public static void main(String[] args) {
		String fileName = "D:\\sqlResult_5500.txt";
		String fileWrite = "D:\\sqlResult_5501.txt";
		File file = new File(fileName);
		BufferedReader reader = null;
		FileWriter writer;
		try {
			System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			writer = new FileWriter(fileWrite, true);
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// System.out.println(line + " " + tempString);
				String[] temp = tempString.split(",");
				String alipayUserId = temp[0];
				String oldApCode = temp[2];
				String apCode = generateApNum(alipayUserId);
				String content = alipayUserId + "," + oldApCode + "," + apCode + "\n";
				System.out.println(line + ": " + content);
				writer.write(content);

				line++;

			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
