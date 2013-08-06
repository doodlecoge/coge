package com.szwx.yht.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 去除空格工具类
 * @author zhangyj
 * @date Mar 20, 2012
 */
public class BlankTool {

	public static String normalizeDigit(String s) {
		StringBuilder sb = new StringBuilder();
		for (Character c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append('0');
			} else
				sb.append(c);
		}
		return sb.toString();
	}
	/**
	 * 去除空格
	 * @author zhangyj
	 * @date Mar 20, 2012
	 */
	public static String stripBlank(String s) {
		StringBuilder sb = new StringBuilder();
		for (Character c : s.toCharArray()) {
			if (c != ' ')
				sb.append(c);
		}
		return sb.toString();
	}
	/**
	 * 去除所有空格
	 * @author zhangyj
	 * @date Mar 20, 2012
	 */
	public static String removeBlank(String s) {
		String s1=removeBlankLine(s);
		String s2=stripBlank(s1);
		return s2;
	}
	/**
	 * 去除空格行
	 * @author zhangyj
	 * @date Mar 20, 2012
	 */
	public static String removeBlankLine(String so) {

		Pattern pattern = Pattern.compile("[\\s\\p{Zs}]");
		Matcher re = pattern.matcher(so);
		so = re.replaceAll("");

		return so;

	}

	public static String htmlToTxt(String htmlStr) {
		String result = "";
		boolean flag = true;
		if (htmlStr == null) {
			return "";
		}

		htmlStr = htmlStr.replace("\"", ""); // ȥ�����

		char[] a = htmlStr.toCharArray();
		int length = a.length;
		for (int i = 0; i < length; i++) {
			if (a[i] == '<') {
				flag = false;
				continue;
			}
			if (a[i] == '>') {
				flag = true;
				continue;
			}
			if (flag == true) {
				result += a[i];
			}
		}
		return result.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(BlankTool.stripBlank("15 dd d@RFDC1563 2"));

	}

}
