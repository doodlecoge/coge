package com.szwx.yht.util;

import java.util.*;

/**
 * 字符操作工具类
 * @author zhangyj
 * @date Mar 20, 2012
 */
public class StringUtil {

	public static int getIntValue(String s, int errorValue) {
		int ok = 0;
		try {
			ok = Integer.parseInt(s.trim());
		} catch (NumberFormatException e) {
			ok = errorValue;
		}
		return ok;
	}

	public static long getLongValue(String s, int errorValue) {
		long ok = 0;
		try {
			ok = Long.parseLong(s.trim());
		} catch (NumberFormatException e) {
			ok = errorValue;
		}
		return ok;
	}

	public static String getStrFromArray(String[] s, String splite) {
		String ok = "";
		if (s == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append(s[k] + splite);
		}
		ok = buffer.toString();
		if (ok.endsWith(splite)) {
			ok = ok.substring(0, ok.length() - splite.length());
		}
		return ok;
	}

	public static String getInCharSql(String s1, String splite) {
		if (isNull(s1)) {
			return "";
		}
		String[] s = s1.split(splite);
		if (isNull(s)) {
			return "";
		}
		String ok = "";
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append("'" + s[k] + "',");
		}
		ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		if (ok.startsWith(",")) {
			ok = ok.substring(1);
		}
		return ok;
	}

	/**
	 * 得到字符串的IN类型where查询条件
	 * 
	 * @param s
	 * @return
	 */
	public static String getVarcharInSQL(String[] s) {

		if (s == null) {
			return "";
		}

		if (s.length < 1) {
			return "";
		}
		if (s.length == 1 && StringUtil.isNull(s[0])) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append("'" + s[k] + "',");
		}
		String ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		return ok;
	}

	/**
	 * 得到数字类型的IN类型where查询条件
	 * 
	 * @param s
	 * @return
	 */
	public static String getNumberInSQL(String[] s) {

		if (s == null) {
			return "";
		}

		if (s.length < 1) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.length; k++) {
			buffer.append(s[k] + ",");
		}
		String ok = buffer.toString();
		if (ok.endsWith(",")) {
			ok = ok.substring(0, ok.length() - 1);
		}
		return ok;
	}
	@SuppressWarnings("unchecked")
	public static List getSpliteList(String s1, String splite) {
		if (StringUtil.isNull(s1)) {
			return new ArrayList();
		}
		String[] s = s1.split(splite);
		if (s == null) {
			return new ArrayList();
		}
		List list = Arrays.asList(s);

		return list;
	}
	@SuppressWarnings("unchecked")
	public static String getStrFromList(List s, String splite) {
		String ok = "";
		if (StringUtil.isNull(s)) {
			return ok;
		}
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < s.size(); k++) {
			String curValue = (String) s.get(k);
			if (!StringUtil.isNull(curValue)) {
				buffer.append(curValue.trim() + splite);
			}
		}
		ok = buffer.toString();
		if (ok.endsWith(splite)) {
			ok = ok.substring(0, ok.length() - splite.length());
		}
		return ok;
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toNull(String s) {
		if (StringUtil.isNull(s)) {
			return "";
		}
		return s;
	}
	
	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toNull(Object s) {
		if (s==null) {
			return "";
		}
		return toNull(s.toString());
	}
	
	
	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toTrim(String s) {
		if (StringUtil.isNull(s)) {
			return "";
		}
		return s.trim();
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param s
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toNull(String s, String repacle) {
		if (StringUtil.isNull(s)) {
			return repacle;
		}
		return s;
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param s
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toZero(int s, String repacle) {
		if (s == 0) {
			return repacle;
		}
		return String.valueOf(s);
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String getObjStr(Object s1, String repacle) {
		if (s1 == null) {
			return repacle;
		}
		String s = String.valueOf(s1);
		if (StringUtil.isNull(s)) {
			return repacle;
		}
		return s;
	}

	/**
	 * 判断字串符是空串或NULL,"null"
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null,"null",其返回结果是"";
	 */
	public static String toObjNull(Object s1) {
		return getObjStr(s1, "");
	}

	public static String toNullHtml(String s) {
		if (StringUtil.isNull(s)) {
			return "&nbsp;";
		}
		return s;
	}

	public static String getTelNoSplit(String tel) {
		String telOk = "";
		if (tel == null || "".equals(tel) || "-".equals(tel)) {
			return "";
		}
		if (tel.indexOf("-") > -1) {
			String[] s = tel.split("-");
			if (s != null) {
				if (s.length == 2) {
					String pre = s[0];
					String fre = s[1];
					telOk = pre + fre;
				}
			}
		}
		return telOk;
	}

	/**
	 * 判断字串符是空串或NULL
	 * 
	 * @param _str
	 *            字符串
	 * @return 是否是空串或null
	 */
	public static boolean isNull(String src) {
		if (src == null) {
			return true;
		}
		String s = src;
		s = s.trim();
		if (s.equals("")) {
			return true;
		}
		if (s.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象数组是否为空
	 * 
	 * @param obj
	 *            字符串
	 * @return 是否是空串或null
	 */
	public static boolean isNull(Object[] obj) {
		if (obj == null) {
			return true;
		}
		if (obj.length == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isNull(Map list) {
		if (list == null) {
			return true;
		}
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isNull(Collection list) {
		if (list == null) {
			return true;
		}
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 复选框或单选按钮选中的操作
	 * 
	 * @param
	 * @return
	 */
	public static String isChecked(String src, String standardValue) {
		if (isNull(standardValue)) {
			return "";
		}
		if (isNull(src)) {
			return "";
		}
		src = src.trim();
		if (src.equals(standardValue)) {
			return "checked";
		}
		return "";
	}
	public static String subString(String str, int length) {
		if (str != null) {
			if (str.length() > length) {
				str = str.substring(0, length);
			}
		} else {
			str = "";
		}
		return str;
	}

	public static int getMaxIntValue() {
		return 999999999;
	}

	public static int getMaxIntValue(int digit) {
		if (digit > 9) {
			return getMaxIntValue();
		}
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < digit; k++) {
			buffer.append("9");
		}
		return Integer.parseInt(buffer.toString());
	}

	public static int getMinIntValue() {
		return -999999999;
	}

	public static String getExpress(String varName, String value) {
		return " " + varName + "='" + value + "' ";
	}

	public static String getChangeLine(String value) {
		return value + "\n";
	}

	public static String insertInfo(String s, String needReplace,
			String insertStr) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(s);
		int startIndex = buffer.indexOf(needReplace);
		if (startIndex > -1) {
			buffer.replace(startIndex, startIndex + needReplace.length(), " ");
			buffer.insert(startIndex, insertStr);
		}
		return buffer.toString();
	}
	
	/**
	 * 是否是数字判断
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
//	    Pattern pattern = Pattern.compile("[0-9]*");  
//	    return pattern.matcher(str).matches();    
		if(isNull(str)){
			return false;
		}
		String str1=str;
		try {
			Double.parseDouble(str1);
		} catch (NumberFormatException e) {
		
			//e.printStackTrace();
			return false;
		}
		
//		for (int i = str1.length();--i>=0;){
//			if (!Character.isDigit(str1.charAt(i))){
//			 return false;
//			}
//		}
		return true;
						
	 } 
	
	/**
	 * 是否是数字判断
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){ 
		if(isNull(str)){
			return false;
		}
		String str1=str;
		try {
			Integer.parseInt(str1);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
						
	 } 
	


}
