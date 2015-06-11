package com.taobao.yinggang.nomal.myJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditContent {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		// 下面是删除字幕文件每一行中\N之后的日语
		try {
			String pathread = "H:\\rcp\\a.txt";
			String pathwrite = "H:\\rcp\\b.txt";

			FileReader fileReader = new FileReader(pathread);
			BufferedReader reader = new BufferedReader(fileReader);
			FileWriter fileWriter = new FileWriter(pathwrite);

			String line = null;
			int nPos = -1;
			while (true) {
				line = reader.readLine();
				if (line == null)
					break;

				// 下面开始分析
				nPos = line.indexOf("\\N");
				// System.out.print(nPos+" ");
				String ChineseTxt = null;
				if (nPos > 0)
					ChineseTxt = line.substring(0, nPos);
				else
					ChineseTxt = line;
				fileWriter.write(ChineseTxt + "\n");

			}// while

			fileWriter.close();
			fileReader.close();
			System.out.println("done");
		} catch (IOException e) {
			System.err.println(e);
		}// try_catch
	}

}
