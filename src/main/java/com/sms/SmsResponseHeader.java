package com.sms;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class SmsResponseHeader {
    @XmlElement(name = "transactionId")
    public String transactionId;

    @XmlElement(name = "ResponseType")
    public String responseType;

    @XmlElement(name = "command")
    public String command;
}
