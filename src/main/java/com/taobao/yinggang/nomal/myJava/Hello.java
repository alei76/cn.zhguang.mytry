package com.taobao.yinggang.nomal.myJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hello {

	/**
	 * �����ַ�������
	 * 
	 * @return buffer :����ɹ� / null :����ʧ��
	 */
	public String StringIn() {
		String buffer;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			buffer = input.readLine();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param args
	 * @author �Ź�
	 */
	public static void main(String[] args) {
		//
		// Statement stmt = null;
		// String driverName = null;// ���ݿ�����
		// String url = null;// ���ݿ��ַ
		// String username = null;
		//
		// String password = null;
		//
		// driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		// url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=IDS";
		// username = "admin";
		// password = "123456";
		//
		// try {
		// Class.forName(driverName);
		// System.out.println("1");
		// Connection conn = DriverManager.getConnection(url, username, password);
		// System.out.println("2");
		// //stmt = conn.createStatement();
		// //System.out.println("3");
		// //String sql = "select * from AlertInfo";
		// //System.out.println("4");
		// //stmt.executeQuery(sql);
		// //System.out.println("5");
		// } catch (ClassNotFoundException e) {
		// //System.out.print(e.toString());
		// e.printStackTrace();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// //System.out.print(e.toString());
		// }
		// String sys = System.getenv("windir");
		// System.out.print(sys);
		// String IP =">0";
		// //String[] str = IP.split(",");
		//
		// //for(int i = 0; i< str.length; i++){
		// // System.out.println(str[0]);
		// //}
		// if(IP.matches(">[0-9]+"))
		// System.out.println("true");
		// else System.out.println("false");
		// System.out.println(Charset.defaultCharset().displayName());

		File dir = new File("G:\\download\\��մ�");
		String[] fName = dir.list();
		for (int i = 0; i < fName.length; i++) {
			// int a =Integer.parseInt(fName[i].substring(0, 2));
			// System.out.println(fName[i].substring(fName[i].length()-7,fName[i].length()));
			File file = new File("G:\\download\\��մ�\\" + fName[i]);
			// if (fName[i].substring(fName[i].length() - 7, fName[i].length())
			// .equals("chs.srt"))
			// file.renameTo(new File("G:\\download\\��մ�\\"
			// + fName[i].subSequence(21, 23) + ".chs.srt"));
			// else
			// file.renameTo(new File("G:\\download\\��մ�\\"
			// + fName[i].subSequence(21, 23) + ".mkv"));
			file.renameTo(new File("G:\\download\\��մ�\\" + fName[i].substring(11)));
		}
		//
		//
		// String str = "tabcde";
		// System.out.print(""+str.indexOf("abcd"));
	}
}
