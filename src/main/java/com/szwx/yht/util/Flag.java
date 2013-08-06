package com.szwx.yht.util;

import java.io.*;
import java.util.Properties;


/**
 * @author lisk
 */
public class Flag {

	/**
	 * 指定property文件
	 */
	private static final String PROPERTY_FILE = "/flag.properties";

	/**
	 * 根据Key 读取Value
	 * 
	 * @param key
	 * @return
	 */
	public static String readData(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					PROPERTY_FILE));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			int len = value.length();
			if (len < 6) {
				for (int i = 0; i < 6 - len; i++) {
					value = "0" + value;
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String read(String key) {
		Properties props = new Properties();
		String filePath=Flag.class.getResource(PROPERTY_FILE).getFile().replaceAll("%20"," ");
		try {
			props.load(new FileInputStream(filePath));
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 订单号取得
	public static String readDDH(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					PROPERTY_FILE));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			int len = value.length();
			if (len < 8) {
				for (int i = 0; i < 8 - len; i++) {
					value = "0" + value;
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 银联订单号取得
	public static String readDDHyl(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					PROPERTY_FILE));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			int len = value.length();
			if (len < 5) {
				for (int i = 0; i < 5 - len; i++) {
					value = "0" + value;
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 招商银行订单号取得
	public static String readDDHzs(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					PROPERTY_FILE));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			int len = value.length();
			if (len < 4) {
				for (int i = 0; i < 4 - len; i++) {
					value = "0" + value;
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改或添加键值对 如果key存在，修改 反之，添加。
	 * 
	 * @param key
	 * @param value
	 */
	public synchronized static void writeData(String key, String value) {
		Properties prop = new Properties();
		try {
			File file = new File(PROPERTY_FILE);
			if (!file.exists())
				file.createNewFile();
			InputStream fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();// 一定要在修改值之前关闭fis
			OutputStream fos = new FileOutputStream(PROPERTY_FILE);
			prop.setProperty(key, value);
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
		} catch (IOException e) {
			System.out.println("Visit " + PROPERTY_FILE + " for updating "
					+ value + " value error");
		}
	}

	public static void main(String[] args) {
		System.out.println(readData("count"));
		writeData("count", String
				.valueOf((Integer.parseInt(readData("count")) + 1)));
		System.out.println(readData("count"));
	}
}