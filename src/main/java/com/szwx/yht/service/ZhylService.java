package com.szwx.yht.service;

import com.hch.security.Config;
import com.szwx.yht.exception.HrsExpression;
import szsx.yhtwebservice.YhtRegPortType;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class ZhylService {
    private final static YhtRegPortType port;

    static {
//        final String serviceUrl = Config.ZhylWsUrl;
        final String serviceUrl = Config.getString("zhyl_ws_url");
        final String namespace = "http://yhtWebService.szsx";
        final String serviceName = "yhtReg";

        URL url = null;

        try {
            url = new URL(serviceUrl);
        } catch (MalformedURLException e) {
            throw new HrsExpression("MalformedURLException: (" + serviceUrl + ")", e);
        }

        QName qName = new QName(namespace, serviceName);
        Service service = Service.create(url, qName);
        port = service.getPort(YhtRegPortType.class);
    }

    private ZhylService() {}

    public static YhtRegPortType getPort() {
        return port;
    }
}
