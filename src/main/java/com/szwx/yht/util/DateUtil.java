package com.szwx.yht.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 日期工具类
 * @author zhangyj
 * @date Aug 9, 2012
 */
@Component("dateUtil")
public class DateUtil {
	
	/**
	 * 反回日期的格式
	 * @author zhangyj
	 * @date Aug 9, 2012
	 * @param date
	 * @return
	 */
	public String sdf(String date){

		if(StringUtil.isNull(date)){
			return "formatError";
		}
		
		Pattern pattern1=Pattern.compile("^\\d{4}(-){1}\\d{1,2}(-){1}\\d{1,2}$");
		if(pattern1.matcher(date).matches()){
			return "yyyy-MM-dd";
		}
		Pattern pattern2=Pattern.compile("^\\d{4}(-){1}\\d{1,2}$");
		if(pattern2.matcher(date).matches()){
			return "yyyy-MM";
		}
		Pattern pattern3=Pattern.compile("^\\d{4}$");
		if(pattern3.matcher(date).matches()){
			return "yyyy";
		}
		
		return "formatError";
	}
	
	public static void main(String[] args) {
		Pattern pattern=Pattern.compile("^\\d{4}(-){1}\\d{1,2}$");
//		Matcher matcher=pattern.matcher("2012-08-08");
		
//		matcher.groupCount();
		System.out.println(pattern.matcher("2012-08-08").matches());
	}
}
