package com.szwx.yht.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 数据库配置文件读取
 * @author zhangyj
 * @date Mar 22, 2012
 */
public class Config {
	public static String dbService="YHT";
	private final String path="/db.properties";
//	private final String path="db.properties";
	private Properties db=new Properties();
	private static Config config=null;
	
	private Config(){
		init();
	}
	
	public static Config newInstance(){
		if(null==config){
			config= new Config();
		}
		return config;
	}
	public Properties getDb() {
		return db;
	}
	private void init(){
		try {
			String filePath=this.getClass().getClassLoader().getResource(path).getFile().replaceAll("%20"," ");
			db.load(new FileInputStream(new File(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
