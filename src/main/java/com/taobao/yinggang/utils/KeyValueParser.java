/*
 * Copyright (c) 2014 by Ying Gang, Tmall, all rights reserved.
 */

package com.taobao.yinggang.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;

/**
 * @project logisticspartner-common
 * @create 2014年2月11日 下午5:17:18
 * @version 1.0.0
 * @author 英冈
 */
public class KeyValueParser {

    private static final String KEY_VALUE_SPLIT = "=";

    private static final String KEY_VALUE_GROUP_SPLIT = ";";

    public static Map<String, String> parseString2Map(String stringData) {
        return parseString2Map(stringData, KEY_VALUE_SPLIT, KEY_VALUE_GROUP_SPLIT);
    }

    public static String parseMap2String(Map<String, String> mapData) throws Exception {
        return parseMap2String(mapData, KEY_VALUE_SPLIT, KEY_VALUE_GROUP_SPLIT);

    }

    public static Map<String, String> parseString2Map(String stringData, String keyValueSplit, String groupSplit) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtil.isBlank(stringData)) {
            return map;
        }
        if (StringUtil.isNotEmpty(stringData)) {
            String[] strList = stringData.split(groupSplit);
            for (String line : strList) {
                String[] kv = line.split(keyValueSplit);
                if (kv.length >= 2) {
                    map.put(kv[0], kv[1]);
                }
            }
        }
        return map;
    }

    public static String parseMap2String(Map<String, String> mapData, String keyValueSplit, String groupSplit) throws Exception {
        StringBuilder sb = new StringBuilder(512);
        if (mapData == null) {
            return sb.toString();
        }
        for (Map.Entry<String, String> mapEntry : mapData.entrySet()) {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();

            dataFormatCheck(key, keyValueSplit, groupSplit);
            dataFormatCheck(value, keyValueSplit, groupSplit);

            sb.append(key);
            sb.append(KEY_VALUE_SPLIT);
            sb.append(value);
            sb.append(KEY_VALUE_GROUP_SPLIT);
        }
        return sb.toString();
    }

    private static void dataFormatCheck(String str, String keyValueSplit, String groupSplit) throws Exception {
        if (str == null) {
            return;
        }
        if (str.indexOf(groupSplit) != -1 || str.indexOf(keyValueSplit) != -1) {
            throw new Exception("PLF_KV_ILLEGAL_01 , character ':' or ';' key value structure is not allowed:[" + str + "]");
        }
    }

}
