/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: mytry
 * 
 * APGenerate.java File Created at 下午6:00:32
 * 
 * 
 * Copyright 2014 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.taobao.yinggang.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dbunit.dataset.csv.CsvParser;
import org.dbunit.dataset.csv.CsvParserException;
import org.dbunit.dataset.csv.CsvParserImpl;

/**
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月6日
 */
public class APGenerate {

	/**
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		CsvParser csvParser = new CsvParserImpl();
		File file = new File("D:\\sqlResult_5500.csv");
		List<?> list = null;
		try {
			list = csvParser.parse(file);

		} catch (CsvParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < 100; i++) {
			String alipay_user_id = getCell(list, i, "alipay_user_id");
			String transporter_id = getCell(list, i, "transporter_id");
			String address2 = getCell(list, i, "address2");
			System.out.println(alipay_user_id + transporter_id + address2);
		}

	}

	private static String getCell(List list, int rows, String colum) {
		List<String> titles = (List<String>) list.get(0);
		int col = 0;
		for (String title : titles) {
			if (colum.equals(title)) {
				break;
			} else {
				col++;
			}
		}
		List<String> row = (List<String>) list.get(rows);
		return row.get(col);
	}
}
