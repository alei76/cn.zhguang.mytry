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
		// TODO �Զ����ɷ������
		// ������ɾ����Ļ�ļ�ÿһ����\N֮�������
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

				// ���濪ʼ����
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
