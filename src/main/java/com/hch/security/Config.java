package com.hch.security;

import com.szwx.yht.AppListener;
import com.szwx.yht.exception.HrsExpression;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    public static Logger logger = LoggerFactory.getLogger("GLOBAL LOGGER");

//    public static int RegLimit = 10;
//    public static int QueueSize = 2000;
//
//    public static int TimeoutMin = 1;
//    public static int MaxSessionPerIp = 1;
//
//    public static int RegGapSec = 2;
//
//    public static String SmsIp = "192.168.10.78";
//    public static int SmsPort = 8900;
//    public static String SmsUsername = "XXZX";
//    public static String SmsPassword = "XXZX2013";
////    public static String ZhylWsUrl = "http://192.168.10.76:8080/zhylService/services/yhtReg";
//    public static String ZhylWsUrl = "http://172.25.223.76:8080/zhylService/services/yhtReg";
//


    private static Map<String, String> map = null;

    public static int getInt(String key) {
        String val = getString(key);
        return Integer.valueOf(val);
    }

    public static String getString(String key) {
        if (map == null) {
            reloadProperties();
        }

        return map.get(key);
    }

    public static void reloadProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(AppListener.WebRoot + "/" + "sys-config.properties"));
            Set<String> names = props.stringPropertyNames();
            map = new HashMap<String, String>();
            for (String name : names) {
                map.put(name, props.getProperty(name));
            }
        } catch (IOException e) {
            throw new HrsExpression(e);
        }
    }

    public static Map<String, String> getProperties() {
        return map;
    }
}
