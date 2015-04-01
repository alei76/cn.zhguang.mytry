package com.taobao.yinggang.jip;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dbunit.dataset.csv.CsvParser;
import org.dbunit.dataset.csv.CsvParserException;
import org.dbunit.dataset.csv.CsvParserImpl;

import com.alibaba.common.lang.StringEscapeUtil;
import com.taobao.yinggang.utils.KeyValueParser;

public class JIPMessageTest {

	private static String url = "http://logistics.open.alipay.com/atpapps/logisticsMsgProcessor.htm";

	// private static String url = "http://htkglobal.com/tm/receiver";

	// private static String url = "http://partner.taobao.com/gateway/express_message_receiver.do";

	public static void Cancel() {
		String msg_type = "tmall.logistics.event.atp.cancel";
		String s = "<logisticsEventsRequest>" + "<logisticsEvent>" + "<eventHeader>"
				+ "<eventType>LOGISTICS_ATP_CANCEL</eventType>" + "<eventTime>2014-05-12 09:50:02</eventTime>"
				+ "<eventSource>ATP</eventSource>" + "<eventTarget>Taobao</eventTarget>" + "</eventHeader>"
				+ "<eventBody>" + "<order>" + "<logisticsOrderId>LP00023044188020</logisticsOrderId>"
				+ "<companyId>21000025056</companyId>" + "<alipayUserId>2088602007684274</alipayUserId>"
				+ "<wareHouseCode>PAKKING-0001</wareHouseCode>" + "<remark>Cancel</remark>"
				+ "<occurTime>2014-05-12 09:50:02</occurTime>" + "</order>" + "</eventBody>" + "</logisticsEvent>"
				+ "</logisticsEventsRequest>";

		sendClient(s, msg_type);
	}

	public static void cancelEnsure() {
		String msg_type = "tmall.logistics.event.atp.cancelensure";
		String s = "<logisticsEventsRequest>" + "<logisticsEvent>" + "<eventHeader>"
				+ "<eventType>LOGISTICS_ATP_CANCELENSURE</eventType>" + "<eventTime>2014-06-27 10:55:01</eventTime>"
				+ "<eventSource>Taobao</eventSource>" + "<eventTarget>ATP</eventTarget>" + "</eventHeader>"
				+ "<eventBody>" + "<order>" + "<logisticsOrderId>LP00024372503023</logisticsOrderId>"
				+ "<companyId>21000025056</companyId>" + "<alipayUserId>2088002054035755</alipayUserId>"
				+ "<wareHouseCode>PAKKING-0001</wareHouseCode>" + "<state>1</state>" + "<remark>OK</remark>"
				+ "<occurTime>2014-06-27 10:55:01</occurTime>" + "</order>" + "</eventBody>" + "</logisticsEvent>"
				+ "</logisticsEventsRequest>";
		sendClient(s, msg_type);
	}

	public static void atpStorage() {
		String msg_type = "tmall.logistics.event.atp.storage";
		String s = "<logisticsEventsRequest>" + "<logisticsEvent>" + "<eventHeader>"
				+ "<eventType>LOGISTICS_ATP_STORAGE</eventType>" + "<eventTime>2014-05-23 12:01:01</eventTime>"
				+ "<eventSource>Taobao</eventSource>" + "<eventTarget>ATP</eventTarget>" + "</eventHeader>"
				+ "<eventBody>" + "<order>" + "<logisticsOrderId>LP00023392243852</logisticsOrderId>"
				+ "<companyId>21000025056</companyId>" + "<alipayUserId>2088002213651080</alipayUserId>"
				+ "<storageState>1</storageState>" + "<remark></remark>"
				+ "<wareHouseCode>PAKKING-0001</wareHouseCode>" + "<freight>7100</freight>"
				+ "<overTenancy>0</overTenancy>" + "<totalExpense>7100</totalExpense>" + "<currency>CNY</currency>"
				+ "<weight>925</weight>" + "<weightType>g</weightType>" + "<length>0</length>" + "<height>0</height>"
				+ "<width>0</width>" + "<linearType>cm</linearType>" + "<imageUrl></imageUrl>" + "<feature></feature>"
				+ "<occurTime>2014-05-23 12:01:01</occurTime>" + "</order>" + "</eventBody>" + "</logisticsEvent>"
				+ "</logisticsEventsRequest>";
		sendClient(s, msg_type);
	}

	public static void consign() {
		String msg_type = "tmall.logistics.event.wms.send";
		String s = "<logisticsEventsRequest>" + "<logisticsEvent>" + "<eventHeader>"
				+ "<eventType>LOGISTICS_SEND_GOODS</eventType>" + "<eventTime>2014-05-30 08:00:00</eventTime>"
				+ "<eventSource>ATP</eventSource>" + "<eventTarget>PAKKING-0001</eventTarget>" + "</eventHeader>"
				+ "<eventBody>" + "<tradeDetail>" + "<tradeOrders>" + "<tradeOrder>"
				+ "<tradeOrderId>29980</tradeOrderId>" + "</tradeOrder>" + "</tradeOrders>" + "</tradeDetail>"
				+ "<logisticsDetail>" + "<logisticsOrders>" + "<logisticsOrder>"
				+ "<taobaoLogisticsId>LP00020351150795</taobaoLogisticsId>"
				+ "<occurTime>2014-05-30 08:00:00</occurTime>" + "<logisticsRemark>Remark</logisticsRemark>"
				+ "</logisticsOrder>" + "</logisticsOrders>" + "</logisticsDetail>" + "</eventBody>"
				+ "</logisticsEvent>" + "</logisticsEventsRequest>";
		sendClient(s, msg_type);
	}

	public static void atpConsignList() {
		String msg_type = "tmall.logistics.event.atp.consign";
		CsvParser csvParser = new CsvParserImpl();
		File file = new File("D:\\result.csv");
		List list = null;
		try {
			list = csvParser.parse(file);

		} catch (CsvParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			String lpCode = getCell(list, i + 1, "order_code");
			String feature = getCell(list, i + 1, "feature");
			Map<String, String> map = KeyValueParser.parseString2Map(feature);
			String alipayUserId = map.get("alipayUserId");
			String tpCompanyId = map.get("tpCompanyId");
			String tradeId = getCell(list, i + 1, "trade_id");
			String warehouseCode = getCell(list, i + 1, "company_code");
			String freight = getCell(list, i + 1, "freight");
			String overTenancy = "0";
			String totalExpense = getCell(list, i + 1, "freight");
			String weight = getCell(list, i + 1, "weight");
			String mailNo = getCell(list, i + 1, "mail_no");
			String weightType = "g";
			String length = "0";
			String height = "0";
			String width = "0";
			String imageUrl = "";
			String s = "<logisticsEventsRequest>" + "<logisticsEvent>" + "<eventHeader>"
					+ "<eventType>LOGISTICS_ATP_CONSIGN</eventType>" + "<eventTime>2012-04-25 17:01:01</eventTime>"
					+ "<eventSource>taobao</eventSource>" + "<eventTarget>ATP</eventTarget>" + "</eventHeader>"
					+ "<eventBody>" + "<order>" + "<logisticsOrderId>" + lpCode + "</logisticsOrderId>" + "<companyId>"
					+ tpCompanyId + "</companyId>" + "<alipayUserId>" + alipayUserId + "</alipayUserId>" + "<mailNo>"
					+ mailNo + "</mailNo>" + "<wareHouseCode>" + warehouseCode + "</wareHouseCode>"
					+ "<occurTime>2014-04-25 17:01:01</occurTime>" + "</order>" + "</eventBody>" + "</logisticsEvent>"
					+ "</logisticsEventsRequest>";
			sendClient(s, msg_type);
		}
	}

	public static void consignList() {
		String msg_type = "tmall.logistics.event.wms.send";
		CsvParser csvParser = new CsvParserImpl();
		File file = new File("D:\\result.csv");
		List list = null;
		try {
			list = csvParser.parse(file);

		} catch (CsvParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			String lpCode = getCell(list, i + 1, "order_code");
			String feature = getCell(list, i + 1, "feature");
			Map<String, String> map = KeyValueParser.parseString2Map(feature);
			String alipayUserId = map.get("alipayUserId");
			String tpCompanyId = map.get("tpCompanyId");
			String tradeId = getCell(list, i + 1, "trade_id");
			String warehouseCode = getCell(list, i + 1, "company_code");
			String freight = getCell(list, i + 1, "freight");
			String overTenancy = "0";
			String totalExpense = getCell(list, i + 1, "freight");
			String weight = getCell(list, i + 1, "weight");
			// String mailNo = getCell(list, i + 1, "mail_no");
			String weightType = "g";
			String length = "0";
			String height = "0";
			String width = "0";
			String imageUrl = "";
			String s = "<logisticsEventsRequest>" + "<logisticsEvent>" + "<eventHeader>"
					+ "<eventType>LOGISTICS_SEND_GOODS</eventType>" + "<eventTime>2014-05-21 08:00:00</eventTime>"
					+ "<eventSource>ATP</eventSource>" + "<eventTarget>"
					+ warehouseCode
					+ "</eventTarget>"
					+ "</eventHeader>"
					+ "<eventBody>"
					+ "<tradeDetail>"
					+ "<tradeOrders>"
					+ "<tradeOrder>"
					+ "<tradeOrderId>"
					+ tradeId
					+ "</tradeOrderId>"
					+ "</tradeOrder>"
					+ "</tradeOrders>"
					+ "</tradeDetail>"
					+ "<logisticsDetail>"
					+ "<logisticsOrders>"
					+ "<logisticsOrder>"
					+ "<taobaoLogisticsId>"
					+ lpCode
					+ "</taobaoLogisticsId>"
					+ "<occurTime>2014-05-21 08:00:00</occurTime>"
					+ "<logisticsRemark>Remark</logisticsRemark>"
					+ "</logisticsOrder>"
					+ "</logisticsOrders>"
					+ "</logisticsDetail>"
					+ "</eventBody>"
					+ "</logisticsEvent>" + "</logisticsEventsRequest>";

			sendClient(s, msg_type);
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

	public static void sendClient(String logistics_interface, String msg_type) {
		try {
			// String rawStr = logistics_interface + "815fcc709a";
			String rawStr = logistics_interface + "3d2a336892";
			String data_digest = new String(Base64.encodeBase64(MD5Bytes(rawStr.getBytes("utf-8"))), "utf-8");
			String logistic_provider_id = "ATP";
			// String logistic_provider_id = "Pakking";
			String target = url + "?" + "_input_charset=utf-8" + "&logistics_interface="
					+ StringEscapeUtil.escapeURL(logistics_interface, "utf-8") + "&data_digest="
					+ StringEscapeUtil.escapeURL(data_digest, "utf-8") + "&logistic_provider_id="
					+ logistic_provider_id + "&msg_type=" + StringEscapeUtil.escapeURL(msg_type, "utf-8")
					+ "&format=xml&msg_id=" + System.currentTimeMillis() + "&version=2.1";
			HttpClient client = new HttpClient();
			HttpMethod method = new PostMethod(target);
			client.executeMethod(method);
			String responseString = method.getResponseBodyAsString();
			// System.out.println(method.getStatusCode());
			System.out.println(responseString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] MD5Bytes(byte[] source) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(source);
			byte[] digest = messageDigest.digest();
			return digest;
		} catch (Exception e) {
			return null;
		}
	}

	// 将指定byte数组以16进制的形式打印到控制台
	public static void printHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toLowerCase());
		}

	}

	public static void main(String[] args) {
		JIPMessageTest.cancelEnsure();

		// printHexString(MD5Bytes("BANMA".getBytes()));
		// CsvParser csvParser = new CsvParserImpl();
		// File file = new File("D:\\result.csv");
		// try {
		// List list = csvParser.parse(file);
		// List title = (List) list.get(0);
		// System.out.println(list.size());
		// System.out.println(title.size());
		// } catch (CsvParserException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
