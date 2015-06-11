/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class UrlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String endpoint = "http://127.0.0.1:8080/mpe/sms/serviecs?test=aabb&ip=ccdd";
		String fileString = "/C:/windows/test.txt";
		URL url;
		try {
			url = new URL(fileString);
			System.out.println(url.getAuthority());
			System.out.println(url.getHost());
			System.out.println(url.getPort());
			System.out.println(url.getProtocol());
			System.out.println(url.getPath());
			System.out.println("query= " + url.getQuery());
			System.out.println(url.getFile());
			System.out.println(url.getRef());
			System.out.println(url.getUserInfo());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
