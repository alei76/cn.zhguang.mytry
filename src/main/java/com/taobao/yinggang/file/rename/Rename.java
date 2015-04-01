/**
 * 
 */
package com.taobao.yinggang.file.rename;

import java.io.File;

/**
 * ����������
 * 
 * @author �Ź�
 */
public class Rename {
	/**
	 * ����dir�������ļ�����������
	 * 
	 * @param dir
	 */
	public void renameAll(File dir, String newName) {

		String[] fName = dir.list();
		for (int i = 0; i < fName.length; i++) {
			File file = new File(dir.getPath() + "\\" + fName[i]);
			if (!file.isDirectory())
				file.renameTo(new File(dir.getPath() + newName));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dir = new File("G:\\download\\��մ�");
		new Rename().renameAll(dir, "");
	}

}
