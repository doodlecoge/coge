package com.szwx.yht.action;

import com.hch.security.Config;
import com.opensymphony.xwork2.ActionSupport;
import com.sms.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-24
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */

@Controller("sms_vc")
public class SendValidationCodeMessageAction extends ActionSupport implements SessionAware {
    private static final Logger log = LoggerFactory.getLogger(SendValidationCodeMessageAction.class);

    public static final Map<String, Long> smsHis = new HashMap<String, Long>();
    public static long lastCleanTime = 0;
    private Map<String, Object> jsonMap;
    private String phone;

    @Override
    public String execute() throws Exception {
        long now = Calendar.getInstance().getTimeInMillis();

        // clean history every hour
        if (now - lastCleanTime > 1000 * 60 * 60) {

            for (String s : smsHis.keySet()) {
                Long aLong = smsHis.get(s);
                if (now - aLong > 1000 * Config.getInt("sms_verify_time_gap")) {
                    smsHis.remove(s);
                }
            }

            lastCleanTime = now;
        }


        HttpServletRequest request = ServletActionContext.getRequest();
        String addr = request.getRemoteAddr();
        jsonMap = new HashMap<String, Object>();

        if (smsHis.containsKey(addr)) {
            long ts = smsHis.get(addr);
            log.debug(addr + ": " + ts);
            if (now - ts < 1000 * Config.getInt("sms_verify_time_gap")) {
                long sec = 60L - (now - ts) / 1000;
                jsonMap.put("sec", sec);
                jsonMap.put("success", false);

                return "json";
            }
        }

        String code = randomCode();
        String msg = Config.getString("sms_verify_msg").replace("{code}", code);

        try {
            SmsRequest smsRequest = new SmsRequest();
            smsRequest.body = new SmsRequestBody(phone, msg);
            smsRequest.header = new SmsRequestHeader();

            // todo: uncomment this to send short message
            //SmsResponse smsResponse = SmsUtils.sendShortMessage(smsRequest);
            //log.debug("send validation code result: " + smsResponse.body.result);


            session.put("validation_code", code);
            log.error("==========" + code);
            jsonMap.put("success", true);

            smsHis.put(addr, Calendar.getInstance().getTimeInMillis());

        } catch (Exception e) {
            log.error("send validate code error", e);
            jsonMap.put("success", false);
            jsonMap.put("msg", e.getMessage());
        }

        jsonMap.put("sec", Config.getInt("sms_verify_time_gap"));


        return "json";
    }


    private String randomCode() {
        String code = "01235689";
        Random rnd = new Random();
        String str = "";

        for (int i = 0; i < 6; i++) {
            int idx = rnd.nextInt(code.length());
            str += code.charAt(idx);
        }

        return str;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }
}
