package com.sms;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class SmsResponseBody {
    @XmlElement(name = "Result")
    public String result;
}
