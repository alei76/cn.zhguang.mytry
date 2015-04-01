package com.taobao.yinggang.dukun;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Feature Tools
 */
public class FeatureUtils {

	public static final String SP = ";";

	public static final String SSP = ":";

	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * map to string
	 *
	 * @param map the map
	 * @return the string
	 */
	public static String toString(final Map<String, String> map) {

		final StringBuilder featureSB = new StringBuilder(";");
		if (MapUtils.isEmpty(map)) {
			return featureSB.toString();
		}

		for (Map.Entry<String, String> entry : map.entrySet()) {

			final String k = entry.getKey();
			final String v = entry.getValue();

			if (null == k
					|| null == v) {
				// 过滤掉无效的kv对
				continue;
			}

			try {
				featureSB
						.append(encode(k, v))
						.append(SP);
			} catch (UnsupportedEncodingException e) {
				//TODO : log this
			}

		}

		return featureSB.toString();

	}

	/**
	 * string to map
	 *
	 * @param featureString the string
	 * @return the map
	 */
	public static Map<String, String> toMap(final String featureString) {
		final Map<String, String> map = new HashMap<String, String>();

		if (StringUtils.isBlank(featureString)) {
			return map;
		}

		for (String kv : StringUtils.split(featureString, SP)) {

			if (StringUtils.isBlank(kv)) {
				// 过滤掉为空的字符串片段
				continue;
			}

			final String[] ar = StringUtils.split(kv, SSP);
			if (ar.length != 2) {
				// 过滤掉不符合K:V单目的情况
				continue;
			}

			final String k = ar[0];
			final String v = ar[1];
			if (StringUtils.isNotBlank(k)
					&& StringUtils.isNotBlank(v)) {
				try {
					decode(map, k, v);
				} catch (UnsupportedEncodingException e) {
					// TODO : log this
				}
			}

		}

		return map;
	}

	public static String encode(final String k, final String v) throws UnsupportedEncodingException {
		return URLEncoder.encode(k, DEFAULT_CHARSET) + SSP + URLEncoder.encode(v, DEFAULT_CHARSET);
	}

	public static void decode(final Map<String, String> map, final String k, final String v) throws UnsupportedEncodingException {
		map.put(URLDecoder.decode(k, DEFAULT_CHARSET), URLDecoder.decode(v, DEFAULT_CHARSET));
	}

	public static void main(String[] args) {

	}

}
