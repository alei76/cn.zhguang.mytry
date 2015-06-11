/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * APGenerate.java File Created at 下午6:16:54
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
package com.taobao.yinggang.file.scd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.google.common.collect.Maps;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月6日
 */
public class ScheduleSQL {
	public static String generateSQL(long entityId) {
		String sql = "update " + TableUtil.getTableName(entityId)
				+ " set status=0,plan_time=0,redo_count=0,priority=0  where entity_id=" + entityId
				+ " and task_type='ccTask' and status!=1;";

		return sql;
	}

	public static void main(String[] args) {
		String fileName = "D:\\ids.txt";
		Map<String, FileWriter> fileWriterMap = Maps.newHashMap();

		File file = new File(fileName);
		BufferedReader reader = null;

		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// System.out.println(line + " " + tempString);
				if (StringUtil.isEmpty(tempString)) {
					continue;
				}
				long entityId = Long.valueOf(StringUtil.trim(tempString));

				String dbName = TableUtil.getDBName(entityId);

				FileWriter writer = fileWriterMap.get(dbName);
				if (writer == null) {
					String fileWrite = "D:\\" + dbName + ".txt";
					writer = new FileWriter(fileWrite, true);
					fileWriterMap.put(dbName, writer);
				}
				String sql = generateSQL(entityId);
				writer.write(sql + "\n");
				System.out.println(line + " : " + entityId + " - " + sql);
				line++;
			}
			for (String dbName : fileWriterMap.keySet()) {
				FileWriter writer = fileWriterMap.get(dbName);
				writer.close();
			}
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
