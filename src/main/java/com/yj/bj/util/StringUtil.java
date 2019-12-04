package com.yj.bj.util;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class StringUtil {
	public static String filterEmoji(String source) {
		if (StringUtils.isNotBlank(source)) {
			return source.replaceAll(
					"[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		} else {
			return source;
		}
	}

	public static String createWechatSign(String characterEncoding,
			SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + "192006250b4c09247ec02edce69f6a2d");
		String sign = MD5Util.getMD5String(sb.toString()).toUpperCase();
		return sign;
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String getFileNameByOOSKey(String oOSKey) {
		return getFullNameByOOSKey(oOSKey).substring(0,
				getFullNameByOOSKey(oOSKey).lastIndexOf("."));
	}

	public static String getFullNameByOOSKey(String oOSKey) {
		return oOSKey.substring(oOSKey.lastIndexOf("/") + 1);
	}

	public static String getFileName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	public static void main(String[] args) {
		String oosKey = "sharejam/pdf/5070b8ae20b1846d7dcbca2d2cc6ebbd.pdf";
		System.out.println(getFileNameByOOSKey(oosKey));
	}
}
