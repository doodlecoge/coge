package com.szwx.yht.util;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {
	//
	public static Date formatDate3(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static XMLGregorianCalendar date2XMLGregorianCalendar(Date date) {
        if (date != null) {
        	Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    		XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl();
    		calendar.setYear(cal.get(Calendar.YEAR));
    		calendar.setMonth(cal.get(Calendar.MONTH) + 1);
    		calendar.setDay(cal.get(Calendar.DAY_OF_MONTH));
    		calendar.setHour(cal.get(Calendar.HOUR_OF_DAY));
    		calendar.setMinute(cal.get(Calendar.MINUTE));
    		calendar.setSecond(cal.get(Calendar.SECOND));
    		calendar.setMillisecond(cal.get(Calendar.MILLISECOND));
    		calendar.setTimezone(cal.get(Calendar.ZONE_OFFSET) / 60000);
    		return calendar;
        }
        return null;
	}
	
	public static XMLGregorianCalendar int2XMLGregorianCalendar(int year,
			int month, int day, int hour, int minute, int second,
			int milliSecond) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// ��
		cal.set(Calendar.MONTH, month - 1);// �£���
		cal.set(Calendar.DAY_OF_MONTH, day);// ��
		cal.set(Calendar.HOUR_OF_DAY, hour);//
		cal.set(Calendar.MINUTE, minute);// ��
		cal.set(Calendar.SECOND, second);// ��
		cal.set(Calendar.MILLISECOND, milliSecond);// ����
		XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl();
		calendar.setYear(cal.get(Calendar.YEAR));
		calendar.setMonth(cal.get(Calendar.MONTH) + 1);
		calendar.setDay(cal.get(Calendar.DAY_OF_MONTH));
		calendar.setHour(cal.get(Calendar.HOUR_OF_DAY));
		calendar.setMinute(cal.get(Calendar.MINUTE));
		calendar.setSecond(cal.get(Calendar.SECOND));
		calendar.setMillisecond(cal.get(Calendar.MILLISECOND));
		calendar.setTimezone(cal.get(Calendar.ZONE_OFFSET) / 60000);

		return calendar;
	}
	
	public static java.sql.Date int2Date(int week) {
		if (week > 7) {
			return null;
		}
		week--;
		
		GregorianCalendar calendar = new GregorianCalendar();
		for (int i = 0; i < 7; i++) {
			Date date = calendar.getTime();
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
			String dateStr = s.format(date);
			int y = Integer.parseInt(dateStr.substring(0, 4));
			int m = Integer.parseInt(dateStr.substring(4, 6));
			int d = Integer.parseInt(dateStr.substring(6, 8));
			if (week == date2Week(y, m, d)) {
				return new java.sql.Date(date.getTime());
			}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		return null;
	}

	public static int date2Week(int year, int month, int day) {
		int y = year;
		int m = month;
		int d = day;
		int e[] = new int[] { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
		int w = (d - 1 + e[m - 1] + y + (y >> 2) - y / 100 + y / 400);
		if (m < 3 && ((y & 3) == 0 && y % 100 != 0 || y % 400 == 0) && y != 0) {
			--w;
		}
		w %= 7;
		return w;
	}

	public static String formatDate1(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date formatDate2(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatDate4(Object date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static List dateList() {
		List list = new ArrayList();
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date1 = calendar.getTime();
		Object[] obj1 = new Object[4];
		obj1[0] = TimeUtil.formatDate1(date1, "yyyy-MM-dd 00:00:00.0");
		obj1[1] = 1;
		obj1[2] = "����";
		obj1[3] = date1;
		Object[] obj2 = new Object[4];
		obj2[0] = TimeUtil.formatDate1(date1, "yyyy-MM-dd 00:00:00.0");
		obj2[1] = 2;
		obj2[2] = "����";
		obj2[3] = date1;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date2 = calendar.getTime();
		Object[] obj3 = new Object[4];
		obj3[0] = TimeUtil.formatDate1(date2, "yyyy-MM-dd 00:00:00.0");
		obj3[1] = 1;
		obj3[2] = "����";
		obj3[3] = date2;
		Object[] obj4 = new Object[4];
		obj4[0] = TimeUtil.formatDate1(date2, "yyyy-MM-dd 00:00:00.0");
		obj4[1] = 2;
		obj4[2] = "����";
		obj4[3] = date2;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date3 = calendar.getTime();
		Object[] obj5 = new Object[4];
		obj5[0] = TimeUtil.formatDate1(date3, "yyyy-MM-dd 00:00:00.0");
		obj5[1] = 1;
		obj5[2] = "����";
		obj5[3] = date3;
		Object[] obj6 = new Object[4];
		obj6[0] = TimeUtil.formatDate1(date3, "yyyy-MM-dd 00:00:00.0");
		obj6[1] = 2;
		obj6[2] = "����";
		obj6[3] = date3;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date4 = calendar.getTime();
		Object[] obj7 = new Object[4];
		obj7[0] = TimeUtil.formatDate1(date4, "yyyy-MM-dd 00:00:00.0");
		obj7[1] = 1;
		obj7[2] = "����";
		obj7[3] = date4;
		Object[] obj8 = new Object[4];
		obj8[0] = TimeUtil.formatDate1(date4, "yyyy-MM-dd 00:00:00.0");
		obj8[1] = 2;
		obj8[2] = "����";
		obj8[3] = date4;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date5 = calendar.getTime();
		Object[] obj9 = new Object[4];
		obj9[0] = TimeUtil.formatDate1(date5, "yyyy-MM-dd 00:00:00.0");
		obj9[1] = 1;
		obj9[2] = "����";
		obj9[3] = date5;
		Object[] obj10 = new Object[4];
		obj10[0] = TimeUtil.formatDate1(date5, "yyyy-MM-dd 00:00:00.0");
		obj10[1] = 2;
		obj10[2] = "����";
		obj10[3] = date5;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date6 = calendar.getTime();
		Object[] obj11 = new Object[4];
		obj11[0] = TimeUtil.formatDate1(date6, "yyyy-MM-dd 00:00:00.0");
		obj11[1] = 1;
		obj11[2] = "����";
		obj11[3] = date6;
		Object[] obj12 = new Object[4];
		obj12[0] = TimeUtil.formatDate1(date6, "yyyy-MM-dd 00:00:00.0");
		obj12[1] = 2;
		obj12[2] = "����";
		obj12[3] = date6;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date7 = calendar.getTime();
		Object[] obj13 = new Object[4];
		obj13[0] = TimeUtil.formatDate1(date7, "yyyy-MM-dd 00:00:00.0");
		obj13[1] = 1;
		obj13[2] = "����";
		obj13[3] = date7;
		Object[] obj14 = new Object[4];
		obj14[0] = TimeUtil.formatDate1(date7, "yyyy-MM-dd 00:00:00.0");
		obj14[1] = 2;
		obj14[2] = "����";
		obj14[3] = date7;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date8 = calendar.getTime();
		Object[] obj15 = new Object[4];
		obj15[0] = TimeUtil.formatDate1(date8, "yyyy-MM-dd 00:00:00.0");
		obj15[1] = 1;
		obj15[2] = "����";
		obj15[3] = date8;
		Object[] obj16 = new Object[4];
		obj16[0] = TimeUtil.formatDate1(date8, "yyyy-MM-dd 00:00:00.0");
		obj16[1] = 2;
		obj16[2] = "����";
		obj16[3] = date8;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date9 = calendar.getTime();
		Object[] obj17 = new Object[4];
		obj17[0] = TimeUtil.formatDate1(date9, "yyyy-MM-dd 00:00:00.0");
		obj17[1] = 1;
		obj17[2] = "����";
		obj17[3] = date9;
		Object[] obj18 = new Object[4];
		obj18[0] = TimeUtil.formatDate1(date9, "yyyy-MM-dd 00:00:00.0");
		obj18[1] = 2;
		obj18[2] = "����";
		obj18[3] = date9;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date10 = calendar.getTime();
		Object[] obj19 = new Object[4];
		obj19[0] = TimeUtil.formatDate1(date10, "yyyy-MM-dd 00:00:00.0");
		obj19[1] = 1;
		obj19[2] = "����";
		obj19[3] = date10;
		Object[] obj20 = new Object[4];
		obj20[0] = TimeUtil.formatDate1(date10, "yyyy-MM-dd 00:00:00.0");
		obj20[1] = 2;
		obj20[2] = "����";
		obj20[3] = date10;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date11 = calendar.getTime();
		Object[] obj21 = new Object[4];
		obj21[0] = TimeUtil.formatDate1(date11, "yyyy-MM-dd 00:00:00.0");
		obj21[1] = 1;
		obj21[2] = "����";
		obj21[3] = date11;
		Object[] obj22 = new Object[4];
		obj22[0] = TimeUtil.formatDate1(date11, "yyyy-MM-dd 00:00:00.0");
		obj22[1] = 2;
		obj22[2] = "����";
		obj22[3] = date11;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date12 = calendar.getTime();
		Object[] obj23 = new Object[4];
		obj23[0] = TimeUtil.formatDate1(date12, "yyyy-MM-dd 00:00:00.0");
		obj23[1] = 1;
		obj23[2] = "����";
		obj23[3] = date12;
		Object[] obj24 = new Object[4];
		obj24[0] = TimeUtil.formatDate1(date12, "yyyy-MM-dd 00:00:00.0");
		obj24[1] = 2;
		obj24[2] = "����";
		obj24[3] = date12;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date13 = calendar.getTime();
		Object[] obj25 = new Object[4];
		obj25[0] = TimeUtil.formatDate1(date13, "yyyy-MM-dd 00:00:00.0");
		obj25[1] = 1;
		obj25[2] = "����";
		obj25[3] = date13;
		Object[] obj26 = new Object[4];
		obj26[0] = TimeUtil.formatDate1(date13, "yyyy-MM-dd 00:00:00.0");
		obj26[1] = 2;
		obj26[2] = "����";
		obj26[3] = date13;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date14 = calendar.getTime();
		Object[] obj27 = new Object[4];
		obj27[0] = TimeUtil.formatDate1(date14, "yyyy-MM-dd 00:00:00.0");
		obj27[1] = 1;
		obj27[2] = "����";
		obj27[3] = date14;
		Object[] obj28 = new Object[4];
		obj28[0] = TimeUtil.formatDate1(date14, "yyyy-MM-dd 00:00:00.0");
		obj28[1] = 2;
		obj28[2] = "����";
		obj28[3] = date14;

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		list.add(obj5);
		list.add(obj6);
		list.add(obj7);
		list.add(obj8);
		list.add(obj9);
		list.add(obj10);
		list.add(obj11);
		list.add(obj12);
		list.add(obj13);
		list.add(obj14);
		list.add(obj15);
		list.add(obj16);
		list.add(obj17);
		list.add(obj18);
		list.add(obj19);
		list.add(obj20);
		list.add(obj21);
		list.add(obj22);
		list.add(obj23);
		list.add(obj24);
		list.add(obj25);
		list.add(obj26);
		list.add(obj27);
		list.add(obj28);
		return list;

	}

	public static List DepartdateList() {
		List list = new ArrayList();
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date1 = calendar.getTime();
		Object[] obj1 = new Object[4];
		obj1[0] = TimeUtil.formatDate1(date1, "yyyy-MM-dd 00:00:00.0");
		obj1[1] = 1;
		obj1[2] = "����";
		obj1[3] = date1;
		Object[] obj2 = new Object[4];
		obj2[0] = TimeUtil.formatDate1(date1, "yyyy-MM-dd 00:00:00.0");
		obj2[1] = 2;
		obj2[2] = "����";
		obj2[3] = date1;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date2 = calendar.getTime();
		Object[] obj3 = new Object[4];
		obj3[0] = TimeUtil.formatDate1(date2, "yyyy-MM-dd 00:00:00.0");
		obj3[1] = 1;
		obj3[2] = "����";
		obj3[3] = date2;
		Object[] obj4 = new Object[4];
		obj4[0] = TimeUtil.formatDate1(date2, "yyyy-MM-dd 00:00:00.0");
		obj4[1] = 2;
		obj4[2] = "����";
		obj4[3] = date2;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date3 = calendar.getTime();
		Object[] obj5 = new Object[4];
		obj5[0] = TimeUtil.formatDate1(date3, "yyyy-MM-dd 00:00:00.0");
		obj5[1] = 1;
		obj5[2] = "����";
		obj5[3] = date3;
		Object[] obj6 = new Object[4];
		obj6[0] = TimeUtil.formatDate1(date3, "yyyy-MM-dd 00:00:00.0");
		obj6[1] = 2;
		obj6[2] = "����";
		obj6[3] = date3;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date4 = calendar.getTime();
		Object[] obj7 = new Object[4];
		obj7[0] = TimeUtil.formatDate1(date4, "yyyy-MM-dd 00:00:00.0");
		obj7[1] = 1;
		obj7[2] = "����";
		obj7[3] = date4;
		Object[] obj8 = new Object[4];
		obj8[0] = TimeUtil.formatDate1(date4, "yyyy-MM-dd 00:00:00.0");
		obj8[1] = 2;
		obj8[2] = "����";
		obj8[3] = date4;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date5 = calendar.getTime();
		Object[] obj9 = new Object[4];
		obj9[0] = TimeUtil.formatDate1(date5, "yyyy-MM-dd 00:00:00.0");
		obj9[1] = 1;
		obj9[2] = "����";
		obj9[3] = date5;
		Object[] obj10 = new Object[4];
		obj10[0] = TimeUtil.formatDate1(date5, "yyyy-MM-dd 00:00:00.0");
		obj10[1] = 2;
		obj10[2] = "����";
		obj10[3] = date5;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date6 = calendar.getTime();
		Object[] obj11 = new Object[4];
		obj11[0] = TimeUtil.formatDate1(date6, "yyyy-MM-dd 00:00:00.0");
		obj11[1] = 1;
		obj11[2] = "����";
		obj11[3] = date6;
		Object[] obj12 = new Object[4];
		obj12[0] = TimeUtil.formatDate1(date6, "yyyy-MM-dd 00:00:00.0");
		obj12[1] = 2;
		obj12[2] = "����";
		obj12[3] = date6;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date7 = calendar.getTime();
		Object[] obj13 = new Object[4];
		obj13[0] = TimeUtil.formatDate1(date7, "yyyy-MM-dd 00:00:00.0");
		obj13[1] = 1;
		obj13[2] = "����";
		obj13[3] = date7;
		Object[] obj14 = new Object[4];
		obj14[0] = TimeUtil.formatDate1(date7, "yyyy-MM-dd 00:00:00.0");
		obj14[1] = 2;
		obj14[2] = "����";
		obj14[3] = date7;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date8 = calendar.getTime();
		Object[] obj15 = new Object[4];
		obj15[0] = TimeUtil.formatDate1(date8, "yyyy-MM-dd 00:00:00.0");
		obj15[1] = 1;
		obj15[2] = "����";
		obj15[3] = date8;
		Object[] obj16 = new Object[4];
		obj16[0] = TimeUtil.formatDate1(date8, "yyyy-MM-dd 00:00:00.0");
		obj16[1] = 2;
		obj16[2] = "����";
		obj16[3] = date8;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date9 = calendar.getTime();
		Object[] obj17 = new Object[4];
		obj17[0] = TimeUtil.formatDate1(date9, "yyyy-MM-dd 00:00:00.0");
		obj17[1] = 1;
		obj17[2] = "����";
		obj17[3] = date9;
		Object[] obj18 = new Object[4];
		obj18[0] = TimeUtil.formatDate1(date9, "yyyy-MM-dd 00:00:00.0");
		obj18[1] = 2;
		obj18[2] = "����";
		obj18[3] = date9;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date10 = calendar.getTime();
		Object[] obj19 = new Object[4];
		obj19[0] = TimeUtil.formatDate1(date10, "yyyy-MM-dd 00:00:00.0");
		obj19[1] = 1;
		obj19[2] = "����";
		obj19[3] = date10;
		Object[] obj20 = new Object[4];
		obj20[0] = TimeUtil.formatDate1(date10, "yyyy-MM-dd 00:00:00.0");
		obj20[1] = 2;
		obj20[2] = "����";
		obj20[3] = date10;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date11 = calendar.getTime();
		Object[] obj21 = new Object[4];
		obj21[0] = TimeUtil.formatDate1(date11, "yyyy-MM-dd 00:00:00.0");
		obj21[1] = 1;
		obj21[2] = "����";
		obj21[3] = date11;
		Object[] obj22 = new Object[4];
		obj22[0] = TimeUtil.formatDate1(date11, "yyyy-MM-dd 00:00:00.0");
		obj22[1] = 2;
		obj22[2] = "����";
		obj22[3] = date11;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date12 = calendar.getTime();
		Object[] obj23 = new Object[4];
		obj23[0] = TimeUtil.formatDate1(date12, "yyyy-MM-dd 00:00:00.0");
		obj23[1] = 1;
		obj23[2] = "����";
		obj23[3] = date12;
		Object[] obj24 = new Object[4];
		obj24[0] = TimeUtil.formatDate1(date12, "yyyy-MM-dd 00:00:00.0");
		obj24[1] = 2;
		obj24[2] = "����";
		obj24[3] = date12;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date13 = calendar.getTime();
		Object[] obj25 = new Object[4];
		obj25[0] = TimeUtil.formatDate1(date13, "yyyy-MM-dd 00:00:00.0");
		obj25[1] = 1;
		obj25[2] = "����";
		obj25[3] = date13;
		Object[] obj26 = new Object[4];
		obj26[0] = TimeUtil.formatDate1(date13, "yyyy-MM-dd 00:00:00.0");
		obj26[1] = 2;
		obj26[2] = "����";
		obj26[3] = date13;

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date14 = calendar.getTime();
		Object[] obj27 = new Object[4];
		obj27[0] = TimeUtil.formatDate1(date14, "yyyy-MM-dd 00:00:00.0");
		obj27[1] = 1;
		obj27[2] = "����";
		obj27[3] = date14;
		Object[] obj28 = new Object[4];
		obj28[0] = TimeUtil.formatDate1(date14, "yyyy-MM-dd 00:00:00.0");
		obj28[1] = 2;
		obj28[2] = "����";
		obj28[3] = date14;

		Date date15 = new Date();
		Object[] obj29 = new Object[4];
		obj29[0] = TimeUtil.formatDate1(date15, "yyyy-MM-dd 00:00:00.0");
		obj29[1] = 1;
		obj29[2] = "����";
		obj29[3] = date15;
		Object[] obj30 = new Object[4];
		obj30[0] = TimeUtil.formatDate1(date15, "yyyy-MM-dd 00:00:00.0");
		obj30[1] = 2;
		obj30[2] = "����";
		obj30[3] = date15;
		list.add(obj29);
		list.add(obj30);
		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		list.add(obj5);
		list.add(obj6);
		list.add(obj7);
		list.add(obj8);
		list.add(obj9);
		list.add(obj10);
		list.add(obj11);
		list.add(obj12);
		list.add(obj13);
		list.add(obj14);
		list.add(obj15);
		list.add(obj16);
		list.add(obj17);
		list.add(obj18);
		list.add(obj19);
		list.add(obj20);
		list.add(obj21);
		list.add(obj22);
		list.add(obj23);
		list.add(obj24);
		list.add(obj25);
		list.add(obj26);
		list.add(obj27);
		list.add(obj28);
		return list;

	}

	public static boolean compareTime(Date date) {
		Date nowTime = new Date();
		Date date1 = TimeUtil.formatDate2(nowTime, "yyyy-MM-dd");
		Date date2 = TimeUtil.formatDate2(date, "yyyy-MM-dd");
		if (date1.compareTo(date2) == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean compareTimeForSendmsg(Date date) {
		Date nowTime = new Date();
		Date date1 = TimeUtil.formatDate2(nowTime, "yyyy-MM-dd");
		Date date2 = TimeUtil.formatDate2(date, "yyyy-MM-dd");
		if (date1.compareTo(date2) == 1) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean compareDate1AndDate2(Date date1,Date date2) {
		Date date11 = TimeUtil.formatDate2(date1, "yyyy-MM-dd");
		Date date21 = TimeUtil.formatDate2(date2, "yyyy-MM-dd");
		if (date11.compareTo(date21) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static long getworkType() {
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), Integer.parseInt(Flag.read("amHour")), Integer.parseInt(Flag.read("amMinute")),0);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), Integer.parseInt(Flag.read("toamHour")), Integer.parseInt(Flag.read("toamMinute")),0);
		Calendar calendar3 = Calendar.getInstance();
		calendar3.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), Integer.parseInt(Flag.read("pmHour")), Integer.parseInt(Flag.read("pmMinute")),0);
		Calendar calendar4 = Calendar.getInstance();
		calendar4.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), Integer.parseInt(Flag.read("topmHour")), Integer.parseInt(Flag.read("topmMinute")),0);
		long worktype = 0;
		if (calendar.getTime().after(calendar1.getTime()) && calendar.getTime().before(calendar2.getTime())) {
			worktype = 1;
		} else if (calendar.getTime().after(calendar3.getTime()) && calendar.getTime().before(calendar4.getTime())) {
			worktype = 2;
		}
		return worktype;
	}
	
	public static boolean isCanReg() {
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), Integer.parseInt(Flag.read("beginHour")),0,0);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), Integer.parseInt(Flag.read("endHour")), 0,0);
		if (calendar.getTime().getHours() >= calendar1.getTime().getHours() && calendar.getTime().getHours() <= calendar2.getTime().getHours()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getAgeByBirth(Date birthday) {
		int csYear = Integer.parseInt(TimeUtil.formatDate1(birthday, "yyyy-MM-dd").substring(0, 4));
		int nowYear = Integer.parseInt(TimeUtil.formatDate1(new Date(), "yyyy-MM-dd").substring(0, 4));
		return nowYear-csYear;
	}
}
