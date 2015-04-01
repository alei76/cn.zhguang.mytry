/**
 * 
 */
package com.taobao.yinggang.file.rename;

import java.io.File;

/**
 * 批量重命名
 * 
 * @author 张广
 */
public class Rename {
	/**
	 * 对于dir下所有文件进行重命名
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
		File dir = new File("G:\\download\\悟空传");
		new Rename().renameAll(dir, "");
	}

}
