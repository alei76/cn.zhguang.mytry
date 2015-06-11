/**
 * 
 */
package com.taobao.yinggang.file.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * ���ڴ���������ʾ�������
 * 
 * @author �Ź�
 */
public class EncodeTrans {
	/**
	 * ����������ʾ���⡣
	 * 
	 * @param str
	 * @return
	 */
	public static String trans(String str) {
		if (str == null || str.equals(""))
			return str;
		String res = null;
		byte[] temp;
		try {
			temp = str.getBytes("gb2312");
			res = new String(temp, "UTF8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		return res;
	}

	public static String encodeStr(String str) {
		String sRes = null;
		try {
			sRes = URLEncoder.encode(str, "GB2312");
			sRes = sRes.replace('+', ' ');
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return sRes;
	}
}
