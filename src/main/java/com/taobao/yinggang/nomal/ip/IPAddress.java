/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class IPAddress {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String regex = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
		Pattern pattern = Pattern.compile(regex);
		List<String> ipList = Lists.newArrayList();
		ipList.add("0.0.0.0");
		ipList.add("8.8.8.8");
		ipList.add("172.168.1.1");
		ipList.add("1.0.1.0");
		ipList.add("255.255.255.255");
		ipList.add("192.168.254.254");
		ipList.add("257.1.1.1");
		ipList.add("1.1.1.257");
		ipList.add("0.0.0.255");
		ipList.add("255.255.255.0");
		ipList.add("398.398.398.398");
		ipList.add("");
		ipList.add("500.50.5.5");

		for (String ip : ipList) {
			System.out.println(ip + " \t:\t " + pattern.matcher(ip).matches());
		}

	}

}
