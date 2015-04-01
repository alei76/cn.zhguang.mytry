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
 * @author 寮犲箍
 */
public class ParseSQL {
	/**
	 * 阃掑綊淇敼鏂囦欢鍐呭
	 * 
	 * @param file
	 */
	public static void appendSqlStmt() {
		String dirPath = "H:\\new\\baofun-line\\";
		String destPath = "H:\\new\\OldLine.txt";
		File dir = new File(dirPath);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(destPath);
			String createTable = " CREATE TABLE OldLine (\nPRODUCTNO Numeric, \nPRODUCTID Char(20), \nPORDER Numeric, \nDEPARTCODE Numeric);\n";
			fileWriter.append(createTable);
			fileWriter.flush();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dir.isDirectory()) {
			String[] fNames = dir.list();
			int total = fNames.length;
			int count = 0;
			for (String fName : fNames) {
				if (fName.endsWith(".sql")) {
					String fPath = dirPath + fName;
					System.out.print("Parse - " + fPath + "...");
					try {
						InputStreamReader fileReader = new InputStreamReader(new FileInputStream(fPath), "gb2312");
						// FileReader fileReader = new FileReader(fPath);
						BufferedReader reader = new BufferedReader(fileReader);
						String line = null;
						while (true) {
							line = reader.readLine();
							if (line == null)
								break;

							// 涓嬮溃寮�ф蓤嗘�
							if (line.startsWith("insert into")) {
								line = "insert into OldLine " + line.substring(line.indexOf("values(")) + "\n";
								fileWriter.append(line);
							}
						}// while
						fileReader.close();
						fileWriter.flush();
						System.out.println("done! -- " + (++count) + " / " + total);
					} catch (IOException e) {
						e.printStackTrace();
					}// try_catch
				}
			}
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" finished!");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 淇敼鏂囦欢鍐呭
		// String path =
		// "C:\\work\\AOP-SW\\dpc-aoptest-trunk\\src\\java\\com\\ericsson\\test\\aopscenarios\\pushcampaign";
		System.out.println("Start...");
		ParseSQL.appendSqlStmt();
		System.out.println("End...");
	}
}
