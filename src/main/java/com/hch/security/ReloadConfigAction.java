package com.hch.security;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 7/18/13
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReloadConfigAction extends ActionSupport {
    private Map<String, String> conf;

    @Override
    public String execute() throws Exception {
        Config.reloadProperties();

        conf = new HashMap<String, String>();

        Field[] fields = Config.class.getDeclaredFields();

        for (Field field : fields) {
            String name = field.getName();
            String value = field.get(name).toString();
            conf.put(name, value);
        }

        return SUCCESS;
    }


//    public void reload() {
//        Properties props = new Properties();
//        props.clear();
//
//        String rootPath = ServletActionContext.getServletContext().getRealPath("/");
//
//        try {
//            FileInputStream is = new FileInputStream(rootPath + "/config/sys-config.properties");
//            props.load(is);
//            is.close();
//        } catch (IOException e) {
//            Config.logger.error("load properties failed", e);
//        }
//
//        String regLimit         = props.getProperty("RegLimit", "1");
//        String queueSize        = props.getProperty("QueueSize", "2000");
//        String timeoutMin       = props.getProperty("TimeoutMin", "5");
//        String maxSessionPerIp  = props.getProperty("MaxSessionPerIp", "20");
//        String regGapSec        = props.getProperty("RegGapSec", "2");
//        String smsPort          = props.getProperty("SmsPort", "8900");
//
//        Config.RegLimit         = Integer.valueOf(regLimit);
//        Config.QueueSize        = Integer.valueOf(queueSize);
//        Config.TimeoutMin       = Integer.valueOf(timeoutMin);
//        Config.MaxSessionPerIp  = Integer.valueOf(maxSessionPerIp);
//        Config.RegGapSec        = Integer.valueOf(regGapSec);
//        Config.SmsPort          = Integer.valueOf(smsPort);
//
//        Config.SmsIp            = props.getProperty("SmsIp", "192.168.10.78");
//        Config.SmsUsername      = props.getProperty("SmsUsername", "XXZX");
//        Config.SmsPassword      = props.getProperty("SmsPassword", "XXZX2013");
//        Config.ZhylWsUrl        = props.getProperty("zhyl_ws_url", "http://192.168.10.76:8080/zhylService/services/yhtReg?wsdl");
//    }


    public Map<String, String> getConf() {
        return conf;
    }

    public void setConf(Map<String, String> conf) {
        this.conf = conf;
    }
}
