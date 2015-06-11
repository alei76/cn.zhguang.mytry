/**
 * 
 */
package com.taobao.yinggang.file.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 用于处理中文显示问题的类
 * 
 * @author 张广
 */
public class EncodeTrans {
	/**
	 * 处理中文显示问题。
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
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return sRes;
	}
}
