package com.taobao.yinggang.file.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.taobao.yinggang.file.delete.Delete;

/**
 * 
 */

/**
 * @author egugzhg
 */
public class MainTest {
	/**
	 * ��셥字符串函�?
	 * 
	 * @return buffer :��셥成功 / null :��셥失败
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
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.print(new Date(System.currentTimeMillis()).toString());
		System.out.print(Delete.class.getSimpleName());
	}

}
