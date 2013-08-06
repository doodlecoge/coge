package com.sms;

import com.hch.security.Config;

import javax.xml.bind.annotation.XmlElement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class SmsRequestHeader {
    @XmlElement(name = "transactionId")
    public String transactionId;

    @XmlElement(name = "userName")
    public String userName;

    @XmlElement(name = "passWord")
    public String passWord;
    
    public SmsRequestHeader(){
//        this.userName = Config.SmsUsername;
//        this.passWord = Config.SmsPassword;
        this.userName = Config.getString("SmsUsername");
        this.passWord = Config.getString("SmsPassword");
        this.transactionId = generateTransactionId();
    }
    
    public SmsRequestHeader(String userName, String passWord){
        this.transactionId = generateTransactionId();
        this.passWord = passWord;
        this.userName = userName;
    }

    public SmsRequestHeader(String transactionId, String userName, String passWord) {
        this.passWord = passWord;
        this.userName = userName;
        this.transactionId = transactionId;
    }
    
    private String generateTransactionId() {
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(time);
    }
}
