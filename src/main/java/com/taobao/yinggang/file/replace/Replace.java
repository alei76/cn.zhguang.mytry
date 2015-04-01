/**
 * 
 */
package com.taobao.yinggang.file.replace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 镟挎崲鏂囦欢涓殑鍐呭
 * 
 * @author 寮犲箍
 */
public class Replace {
	/**
	 * 阃掑綊淇敼鏂囦欢鍐呭
	 * 
	 * @param file
	 */
	public void replaceFilesContent(File file, String oldStr, String newStr) {
		String filePath = file.getPath();
		if (file.isDirectory()) {
			String[] fName = file.list();
			for (int i = 0; i < fName.length; i++) {
				replaceFilesContent(new File(filePath + "\\" + fName[i]), oldStr, newStr);
			}
		} else {// if (file.getName().endsWith(".java")) {
			// 濡傛灉鏄痡ava鏂囦欢锛屽垯淇敼鏂囦欢鍐呭
			System.out.print("Rename-" + file.getPath() + "...");
			try {
				String pathread = filePath;
				String pathwrite = filePath + ".new";

				FileReader fileReader = new FileReader(pathread);
				BufferedReader reader = new BufferedReader(fileReader);
				FileWriter fileWriter = new FileWriter(pathwrite);
				String line = null;
				while (true) {
					line = reader.readLine();
					if (line == null)
						break;

					// 涓嬮溃寮�ф蓤嗘�
					if (line.contains(oldStr)) {
						line = line.replaceAll(oldStr, newStr);
					}
					fileWriter.write(line + "\n");
				}// while

				fileWriter.close();
				fileReader.close();
				System.out.print("done!");
			} catch (IOException e) {
				e.printStackTrace();
			}// try_catch
			System.out.print(" Deleting old file...");
			file.delete();
			File newFile = new File(filePath + ".new");
			newFile.renameTo(new File(filePath));
			System.out.println(" finished!");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 淇敼鏂囦欢鍐呭
		String path = "C:\\work\\AOP-SW\\dpc-aoptest-trunk\\src\\java\\com\\ericsson\\test\\aopscenarios\\pushcampaign";
		System.out.println("Start...");
		new Replace().replaceFilesContent(new File(path), "aopscenariosnew", "aopscenarios");
		System.out.println("End...");
	}

}
