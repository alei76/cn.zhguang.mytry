/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * ComputeIndex.java File Created at 上午11:56:12
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
package com.taobao.yinggang.nomal.list;

import java.util.Arrays;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月10日
 */
public class ComputeIndex {

	/**
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		// int currentServerIndex = 0;
		// int serverCount = 1;
		// int totalCount = 1024;
		// int indexSeg = totalCount / serverCount;
		// List<Integer> list = Lists.newArrayList();
		// int max = currentServerIndex == serverCount - 1 ? totalCount : (currentServerIndex + 1) * indexSeg;
		// for (int i = currentServerIndex * indexSeg; i < max; i++) {
		// list.add(i);
		// }
		// System.out.println(CollectionUtil.collection2String(list, ","));

		// List<Integer> tableIndexList = new ArrayList<Integer>();
		// List<Integer> taskItemList = new ArrayList<Integer>();
		// taskItemList.add(17);
		// int tableCount = 1024;
		// int taskItemNum = 128;
		// if (tableCount % taskItemNum != 0) {
		// System.out.println("Error");
		// return;
		// }
		// int indexSegment = tableCount / taskItemNum;
		// for (Integer itemIndex : taskItemList) {
		// for (int i = itemIndex * indexSegment; i < (itemIndex + 1) * indexSegment; i++) {
		// tableIndexList.add(i);
		// }
		// }
		// System.out.println(CollectionUtil.collection2String(tableIndexList, ","));

		String[] taskList = new String[101];
		for (int i = 0; i < taskList.length; i++) {
			taskList[i] = String.valueOf(i);
		}
		for (int i = 0; i < 2; i++) {
			String[] subTaskList = getSubTaskList(taskList, 2, i);
			System.out.println(Arrays.toString(subTaskList));
		}

	}

	public static String[] getSubTaskList(String[] taskList, int threadNum, int threadIndex) {
		if (threadIndex >= threadNum) {
			threadIndex = threadNum - 1;
		}
		int length = taskList.length;
		int start = (length / threadNum) * threadIndex;
		int end = (length / threadNum) * (threadIndex + 1);
		if (threadNum - 1 == threadIndex) {
			end = taskList.length;
		}

		String[] subTaskList = Arrays.copyOfRange(taskList, start, end);
		return subTaskList;
	}

}
