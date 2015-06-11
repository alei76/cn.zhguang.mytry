/**
 * 
 */
package com.taobao.yinggang.file.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 寮犲箍
 */
public class Search {
	/**
	 * 鎸夋枃浠跺悕阃掑綊镆ユ垒鏂囦欢
	 * 
	 * @param file
	 */
	public void recursiveSearchByFileName(File file, String filename) {
		String filePath = file.getPath();
		if (file.getName().toLowerCase().contains(filename.toLowerCase())) {
			System.out.println("\nFound " + filename + ": " + filePath + "!");
		}
		if (file.isDirectory()) {
			// System.out.println("Searching in directory: "+ filePath+"\\");
			// System.out.print(".");
			String[] fName = file.list();
			for (int i = 0; i < fName.length; i++) {
				recursiveSearchByFileName(new File(filePath + "\\" + fName[i]), filename);
			}
		}
	}

	/**
	 * 鎸夋枃浠跺唴镄勫叧阌瓧阃掑綊镆ユ垒鏂囦欢
	 * 
	 * @param file
	 */
	public void recursiveSearchByKeyword(File file, String keyWord) {
		String filePath = file.getPath();
		if (file.isDirectory()) {
			String[] fName = file.list();
			for (int i = 0; i < fName.length; i++) {
				recursiveSearchByKeyword(new File(filePath + "\\" + fName[i]), keyWord);
			}
		} else {// if (file.getName().endsWith(".java")) {
			try {
				String pathread = filePath;
				FileReader fileReader = new FileReader(pathread);
				BufferedReader reader = new BufferedReader(fileReader);
				String line = null;
				int lineNum = 0;
				while (true) {
					line = reader.readLine();
					lineNum += 1;
					if (line == null)
						break;

					// 涓嬮溃寮�ф蓤嗘�
					if (line.toLowerCase().contains(keyWord.toLowerCase())) {
						System.out.println("Found \"" + keyWord + "\" in: " + file.getPath() + " : line " + lineNum
								+ "!");
						continue;
					}
				}// while

				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}// try_catch
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub"C:\\aopscenarios"
		// 阃掑綊镆ユ垒鏂囦欢
		String path = "C:\\work\\AOP-SW\\dpc-aoptest-trunk";
		System.out.println("Start...");
		// 涓嬮溃鏄寜鏂囦欢涓殑鍏抽敭瀛楁悳绱紝鎼滃埌镄勭粨鏋沧槸杩欎釜鍏抽敭瀛楀嚭鐜板湪鍝釜鏂囦欢镄勫摢涓��
		new Search().recursiveSearchByKeyword(new File(path),
				"fetchAndClickWapAdCheckingBudgetOnFixCampaignWithAllInventories");

		// 涓嬮溃鏄寜鏂囦欢鍚嶆悳绱紝鎼滃埌镄勭粨鏋沧槸杩欎釜鏂囦欢鍚嶅嚭鐜板湪鍝竴涓洰褰?
		// new Search().recursiveSearchByFileName(new File(path),"Overview");
		System.out.println("End...");
	}

}
