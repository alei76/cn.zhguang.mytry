/**
 * 
 */
package com.taobao.yinggang.file.delete;

import java.io.File;

/**
 * @author �Ź�
 */
public class Delete {

	/**
	 * �ݹ�ɾ��ָ��file�������ļ�����Ŀ¼�����ɾ����file��<br/>
	 * �����file�Ǹ��ļ�����ֱ��ɾ��
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
	 * �ݹ�ɾ��fileĿ¼�У���file���ڣ�ָ��delFileName���ļ���Ŀ¼��<br/>
	 * ���file������һ���ļ���������������ֵ���delFileName��Ҳ�ᱻɾ��<br/>
	 * e.g��ɾ��tempĿ¼�µ�����.svnĿ¼�����Ҳ�Ӱ������Ŀ¼��deleteFiles(fileTemp,".svn")
	 * 
	 * @param file ָ��
	 * @param delFilename Ҫɾ�����ļ���Ŀ¼������
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
		// �ݹ�ɾ��.svn
		String path = "C:\\Users\\Administrator\\Desktop\\BaoFeng\\Source";
		System.out.println("Start...");
		new Delete().deleteFiles(new File(path), ".svn");
		System.out.println("End...");
	}

}
