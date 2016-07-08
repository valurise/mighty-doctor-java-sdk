package com.valurise.md.util;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author xiaodong.tan
 */
public class SignUtil {

    public static String sign(Map<String, String> params, String cid, String ts, String key) {
        Map<String, String> newParams = paraFilter(params);
        newParams.put("_cid", cid);
        newParams.put("_ts", ts);
        String preSignStr = createLinkString(newParams);
        String sign = MD5Sign(preSignStr, key, "utf-8");
        return sign;
    }

    private static String MD5Sign(String text, String key, String input_charset) {
        text = text + key;
        return MD5Util.getMD5String(getContentBytes(text, input_charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    private static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("_sign")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    private static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

}
