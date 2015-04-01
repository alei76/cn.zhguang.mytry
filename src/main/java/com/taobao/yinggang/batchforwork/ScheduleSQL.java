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
package com.taobao.yinggang.batchforwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月6日
 */
public class ScheduleSQL {

	private static final String TASK_TABLE = "sc_schedule_task_";

	private static final int TABLE_PER_DB = 128;

	private static final String INDEX_FORMAT = "%04d";

	private static final String TASK_DB = "cainiao_scd_";

	/**
	 * sc_schedule_task_0000
	 * 
	 * @version 1.0
	 * @param entityId
	 * @return
	 */
	private static String getTableName(long entityId) {
		int index = (int) (entityId % 1024);
		StringBuilder sb = new StringBuilder();
		sb.append(TASK_TABLE);
		String tableIndex = String.format(INDEX_FORMAT, index);
		sb.append(tableIndex);
		return sb.toString();
	}

	/**
	 * CAINIAO_SCD_{0000}
	 * 
	 * @version 1.0
	 * @param entityId
	 * @return
	 */
	private static String getDBName(long entityId) {
		int index = (int) (entityId % 1024);
		int dbIndex = index / TABLE_PER_DB;
		StringBuilder sb = new StringBuilder();
		sb.append(TASK_DB);
		String strDBIndex = String.format(INDEX_FORMAT, dbIndex);
		sb.append(strDBIndex);
		return sb.toString();
	}

	private static String generateSql(long entityId) {
		String sql = "update " + getTableName(entityId) + " set plan_time=0,status=0,priority=0,redo_count=0 where entity_id=" + entityId
				+ " and status in (0,2,-2) and task_type='ccTask'  and params like '%tmsOrderDeliverySendTask%'";
		return sql;
	}

	public static void main(String[] args) {
		String fileName = "D:\\tmp\\entityIds.txt";
		File file = new File(fileName);
		String writeParent = file.getParent();
		BufferedReader reader = null;
		Map<String, FileWriter> writers = new HashMap<String, FileWriter>();
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));

			String entityIdStr = null;
			// 一次读入一行，直到读入null为文件结束
			while ((entityIdStr = reader.readLine()) != null) {
				if (entityIdStr.trim().equals("")) {
					continue;
				}
				long entityId = Long.valueOf(entityIdStr.trim());
				String dbName = getDBName(entityId);
				FileWriter writer = writers.get(dbName);
				if (writer == null) {
					writer = new FileWriter(writeParent + "\\" + dbName + ".sql", true);
					writers.put(dbName, writer);
				}
				String sql = generateSql(entityId);
				System.out.println("已处理：" + entityIdStr);
				writer.write(sql + ";\n");
			}
			System.out.println("生成完毕，请到entityIds对应的目录下查看生成的sql文件");
			for (String dbName : writers.keySet()) {
				FileWriter writer = writers.get(dbName);
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
