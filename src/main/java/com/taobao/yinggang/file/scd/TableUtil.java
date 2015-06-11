/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * TableUtil.java File Created at 上午1:24:22
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

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年3月23日
 */
public class TableUtil {
	private static final String TASK_TABLE = "sc_schedule_task_";

	private static final String TASK_DB = "cainiao_scd_";

	private static final int TABLE_PER_DB = 128;

	private static final String INDEX_FORMAT = "%04d";

	/**
	 * sc_schedule_task_0000
	 * 
	 * @version 1.0
	 * @param index
	 * @return
	 */
	public static String getTableName(long entityId) {
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
	 * @param index
	 * @return
	 */
	public static String getDBName(long entityId) {
		int index = (int) (entityId % 1024);
		int dbIndex = index / TABLE_PER_DB;
		StringBuilder sb = new StringBuilder();
		sb.append(TASK_DB);
		String strDBIndex = String.format(INDEX_FORMAT, dbIndex);
		sb.append(strDBIndex);
		return sb.toString();
	}

	/**
	 * "CAINIAO_SCD_{0000}_GROUP"，带双引号
	 * 
	 * @version 1.0
	 * @param index
	 * @return
	 */
	public static int getDBIndex(long entityId) {
		int index = (int) (entityId % 1024);
		int dbIndex = index / TABLE_PER_DB;
		return dbIndex;
	}
}
