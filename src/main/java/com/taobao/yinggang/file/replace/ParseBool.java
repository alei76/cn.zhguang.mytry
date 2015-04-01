/**
 * 
 */
package com.taobao.yinggang.file.replace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 镟挎崲鏂囦欢涓殑鍐呭
 * 
 * @author 寮犲箍
 */
public class ParseBool {
	/**
	 * 阃掑綊淇敼鏂囦欢鍐呭
	 * 
	 * @param file
	 */
	public static void replaceBool(File file) {
		String filePath = file.getPath();
		if (file.isDirectory()) {
			String[] fName = file.list();
			for (int i = 0; i < fName.length; i++) {
				replaceBool(new File(filePath + "\\" + fName[i]));
			}
		} else if (file.getName().endsWith(".sql")) {
			// 濡傛灉鏄痡ava鏂囦欢锛屽垯淇敼鏂囦欢鍐呭
			System.out.print("Replace-" + file.getPath() + "...");
			try {
				String pathread = filePath;
				String pathwrite = filePath + ".new";

				InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathread), "gb2312");
				BufferedReader reader = new BufferedReader(fileReader);
				FileWriter fileWriter = new FileWriter(pathwrite);
				String line = null;
				boolean foundBoolean = false;
				while (true) {
					line = reader.readLine();
					if (line == null)
						break;
					// 涓嬮溃鍦ㄥ墠100琛屼腑镓綛oolean锛屽鏋沧垒涓嶅埌锛屾暣涓枃浠堕兘涓岖敤鍐嶆垒浜?
					// 涓嬮溃寮�ф蓤嗘�
					if (line.contains("Boolean")) {
						foundBoolean = true;
						line = line.replaceAll("Boolean", "bit");
					}
					if (line.contains(",T")) {
						line = line.replaceAll(",T", ",1");
					}
					if (line.contains(",F")) {
						line = line.replaceAll(",F", ",0");
					}
					// line = new String(line.getBytes(),"GBK");
					fileWriter.write(line + "\n");
					fileWriter.flush();

				}// while

				fileWriter.close();
				fileReader.close();
				System.out.print("done!");
				if (foundBoolean == false)
					System.err.print("No Boolean");
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
		String path = "F:\\programs\\SQL\\BaoFeng";
		System.out.println("Start...");
		ParseBool.replaceBool(new File(path));
		System.out.println("End...");
	}

}
