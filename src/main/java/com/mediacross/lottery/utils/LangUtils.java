package com.mediacross.lottery.utils;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;

public final class LangUtils {
	
	private LangUtils(){}
	
	public static String sign(String token, Map<String, String> paramMap) {
		// 拼接有序的参数名-值串
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(token);
		if (MapUtils.isNotEmpty(paramMap)) {
			// 对参数名进行字典排序
			String[] keyArray = paramMap.keySet().toArray(new String[0]);
			Arrays.sort(keyArray);
			for (String key : keyArray) {
				stringBuilder.append(key).append(paramMap.get(key));
			}
		}
	
		// SHA-1编码， 这里使用的是Apache
		// codec，即可获得签名(shaHex()会首先将中文转换为UTF8编码然后进行sha1计算，使用其他的工具包请注意UTF8编码转换)
		/*
		 * 以下sha1签名代码效果等同 byte[] sha =
		 * org.apache.commons.codec.digest.DigestUtils
		 * .sha(org.apache.commons.codec
		 * .binary.StringUtils.getBytesUtf8(codes)); String sign =
		 * org.apache.commons
		 * .codec.binary.Hex.encodeHexString(sha).toUpperCase();
		 */
		String codes = stringBuilder.toString();
		String sign = DigestUtils.shaHex(codes).toUpperCase();
	
		return sign;
	}
}
