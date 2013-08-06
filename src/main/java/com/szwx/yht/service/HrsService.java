package com.szwx.yht.service;

import com.szwx.yht.exception.HrsExpression;
import szsx.provplatwebservice.ProvPlatServicePortType;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午1:10
 * To change this template use File | Settings | File Templates.
 */
public class HrsService {
    private final static String serviceUrl = "http://172.25.223.73//services/ProvPlatService?wsdl";
    private final static String namespace = "http://provPlatWebService.szsx";
    private final static String serviceName = "ProvPlatService";

    private final static Service service;
    private final static HrsService ins = new HrsService();

    static {
        URL url = null;
        try {
            url = new URL(serviceUrl);
        } catch (MalformedURLException e) {
            throw new HrsExpression("MalformedURLException: (" + serviceUrl + ")", e);
        }

        QName qName = new QName(namespace, serviceName);
        service = Service.create(url, qName);
    }



    private HrsService() {
    }

    public static HrsService getInstance() {
        return ins;
    }

    public String getDepartInfo(String xml) {
        ProvPlatServicePortType p = service.getPort(ProvPlatServicePortType.class);
        return p.getDepartInfo(xml);
    }

    public String getHospitalInfo(String xml) {
        ProvPlatServicePortType p = service.getPort(ProvPlatServicePortType.class);
        return p.getHospitalInfo(xml);
    }

    public String getDoctorInfo(String xml) {
        ProvPlatServicePortType p = service.getPort(ProvPlatServicePortType.class);
        return p.getDoctorInfo(xml);
    }

    public String getDoctorWorkInfo(String xml) {
        ProvPlatServicePortType p = service.getPort(ProvPlatServicePortType.class);
        return p.getDoctorWorkInfo(xml);
    }

    public String getDepartWorkInfo(String xml) {
        ProvPlatServicePortType p = service.getPort(ProvPlatServicePortType.class);
        return p.getDepartWorkInfo(xml);
    }
}
