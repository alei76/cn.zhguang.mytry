package com.taobao.yinggang.nomal;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

/**
 * @author ggugzhg
 * @version $Revision: $
 */
public class Test {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	private static Long initCycleTaskPlanTime(Long plantime, Long cycleTime) {
		Long now = System.currentTimeMillis();
		if (plantime == null || plantime == 0L) {
			return now;
		}
		if (plantime + cycleTime >= now) {
			return plantime;
		}
		while (plantime + cycleTime < now) {
			plantime = plantime + cycleTime;
		}
		return plantime;
	}

	public static void main(String[] args) {
		// Long aa = null;
		// if (!(aa instanceof Long)) {
		// System.out.println("OK");
		// }

		// System.out.println(Boolean.valueOf("False"));
		// System.out.println(Boolean.valueOf("false"));
		// System.out.println(Boolean.valueOf("FALSE"));
		//
		// Long a = 1400000000000L;
		// Long cycle = 300000L;
		// Long b = 1409752332610L;
		// a = initCycleTaskPlanTime(a, cycle);
		// System.out.println(System.currentTimeMillis());
		// System.out.println("done" + a);

		// int[] servers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// int[] items = new int[] {};
		// int[] taskNums = new int[] { 2, 2, 2, 2 };
		// int index = 3;
		// int taskNum = taskNums[index];
		// int preTaskNum = 0;
		// for (int i = 0; i < index; i++) {
		// preTaskNum += taskNums[i];
		// }
		//
		// for (int i = preTaskNum; i < preTaskNum + taskNum; i++) {
		// System.out.println(i + " : " + taskNums[i]);
		// }

		// String path1 = "/server/factory";
		// // String path2 = "factory";
		// String path3 = "/factory";
		// System.out.println(ZKPaths.getNodeFromPath(path1));
		// // System.out.println(ZKPaths.getNodeFromPath(path2));
		// System.out.println(ZKPaths.getNodeFromPath(path3));

		Long[] sellerIdList = new Long[] { 9223370016456626203L, 9223370016454594727L, 9223370016454025322L, 9223370016454184206L, 9223370016457937087L,
				9223370016453838126L, 9223370016454531368L, 9223370016454998045L };

		Set<Integer> sellerIdMods = Sets.newHashSet();

		for (Long sellerId : sellerIdList) {
			sellerIdMods.add((int) (sellerId % 1024 / 8));
		}

		List<Integer> sellerIdModsSort = Lists.newArrayList();
		sellerIdModsSort.addAll(sellerIdMods);
		Collections.sort(sellerIdModsSort);
		for (Integer sellerIdMod : sellerIdModsSort) {
			System.out.println(sellerIdMod);

		}
	}
}
