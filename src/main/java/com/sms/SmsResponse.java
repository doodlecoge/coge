package com.sms;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:23
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "Ipf")
public class SmsResponse {
    @XmlElement(name = "header")
    public SmsResponseHeader header;

    @XmlElement(name = "body")
    public SmsResponseBody body;
}
