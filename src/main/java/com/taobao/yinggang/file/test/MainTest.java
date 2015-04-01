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
	 * θΎήμ…¥ε­—η¬¦δΈ²ε‡½ζ•?
	 * 
	 * @return buffer :θΎήμ…¥ζε / null :θΎήμ…¥ε¤±θ΄¥
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
