package com.sms;

import com.szwx.yht.exception.HrsExpression;

import javax.xml.bind.annotation.XmlElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class SmsRequestBody {
    @XmlElement(name = "mobile")
    public String mobile;

    @XmlElement(name = "content")
    public String content;

    @XmlElement(name = "packageType")
    public int packageType;

    public SmsRequestBody() {}

    public SmsRequestBody(String mobile, String content) {
        this.mobile = mobile;
        this.content = content;
        this.packageType = getPackageType();
    }

    private int getPackageType() {
        Pattern ptn = Pattern.compile("^1(3[4-9]|5[012789]|8[78])\\d{8}$");
        Matcher matcher = ptn.matcher(mobile);

        if(matcher.find()) return 1;

        ptn = Pattern.compile("^18[09]\\d{8}$");
        matcher = ptn.matcher(mobile);

        if(matcher.find()) return 0;

        ptn = Pattern.compile("^1[35]3\\d{8}$");         //CDMA
        matcher = ptn.matcher(mobile);

        if(matcher.find()) return 0;


        ptn = Pattern.compile("^1(3[0-2]|5[56]|8[56])\\d{8}$");
        matcher = ptn.matcher(mobile);

        if(matcher.find()) return 2;

        throw new HrsExpression("invalid mobile number: " + mobile);
    }
}
