/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.file;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class WriteSpecialString {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i < 51; i++) {
			System.out.println("    <core name=\"core0" + (i < 10 ? "0" + i : i) + "\" instanceDir=\"core0"
					+ (i < 10 ? "0" + i : i)
					+ "\" config=\"solr/default/conf/solrconfig.xml\" schema=\"solr/default/conf/schema.xml\" />");
		}
	}
}
