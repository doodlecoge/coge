package com.sms;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "Ipf")
public class SmsRequest {
    @XmlElement(name = "header")
    public SmsRequestHeader header;

    @XmlElement(name = "body")
    public SmsRequestBody body;
}
