/**
 * 
 */
package com.taobao.yinggang.file.delete;

import java.io.File;

/**
 * @author 张广
 */
public class Delete {

	/**
	 * 递归删除指定file下所有文件和子目录，最后删除该file；<br/>
	 * 如果该file是个文件，则直接删除
	 * 
	 * @param path
	 * @return
	 */
	public boolean deleteAll(File file) {
		if (file.isDirectory()) {
			String[] fName = file.list();
			if (fName.length != 0) {
				for (int i = 0; i < fName.length; i++) {
					deleteAll(new File(file.getPath() + "\\" + fName[i]));
				}
			}
			return file.delete();
		} else {
			return file.delete();
		}
	}

	/**
	 * 递归删除file目录中（含file在内）指定delFileName的文件或目录；<br/>
	 * 如果file本身是一个文件，则如果它的名字等于delFileName，也会被删除<br/>
	 * e.g：删除temp目录下的所有.svn目录，并且不影响其它目录：deleteFiles(fileTemp,".svn")
	 * 
	 * @param file 指定
	 * @param delFilename 要删除的文件或目录的名字
	 */
	public void deleteFiles(File file, String delFileName) {
		if (file.getName().equals(delFileName)) {
			System.out.print("Delete " + file.getPath() + "...");
			if (deleteAll(file)) {
				System.out.println("Successful");
			} else {
				System.out.println("Failed");
			}
			return;
		} else if (file.isDirectory()) {
			String[] fName = file.list();
			for (int i = 0; i < fName.length; i++) {
				deleteFiles(new File(file.getPath() + "\\" + fName[i]), delFileName);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 递归删除.svn
		String path = "C:\\Users\\Administrator\\Desktop\\BaoFeng\\Source";
		System.out.println("Start...");
		new Delete().deleteFiles(new File(path), ".svn");
		System.out.println("End...");
	}

}
