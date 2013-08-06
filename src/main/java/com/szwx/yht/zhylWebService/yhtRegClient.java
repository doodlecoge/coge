
package com.szwx.yht.zhylWebService;

import com.hch.security.Config;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;

public class yhtRegClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public yhtRegClient() {
        create0();
        Endpoint yhtRegHttpPortEP = service0 .addEndpoint(
                new QName("http://yhtWebService.szsx", "yhtRegHttpPort"),
                new QName("http://yhtWebService.szsx", "yhtRegHttpBinding"),
                Config.getString("zhyl_ws_url"));
//                Config.ZhylWsUrl);
        endpoints.put(new QName("http://yhtWebService.szsx", "yhtRegHttpPort"), yhtRegHttpPortEP);
        Endpoint yhtRegPortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://yhtWebService.szsx", "yhtRegPortTypeLocalEndpoint"), new QName("http://yhtWebService.szsx", "yhtRegPortTypeLocalBinding"), "xfire.local://yhtReg");
        endpoints.put(new QName("http://yhtWebService.szsx", "yhtRegPortTypeLocalEndpoint"), yhtRegPortTypeLocalEndpointEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.szwx.yht.zhylWebService.yhtRegPortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://yhtWebService.szsx", "yhtRegPortTypeLocalBinding"), "urn:xfire:transport:local");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://yhtWebService.szsx", "yhtRegHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
    }

    public yhtRegPortType getyhtRegHttpPort() {
        return ((yhtRegPortType)(this).getEndpoint(new QName("http://yhtWebService.szsx", "yhtRegHttpPort")));
    }

    public yhtRegPortType getyhtRegHttpPort(String url) {
        yhtRegPortType var = getyhtRegHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public yhtRegPortType getyhtRegPortTypeLocalEndpoint() {
        return ((yhtRegPortType)(this).getEndpoint(new QName("http://yhtWebService.szsx", "yhtRegPortTypeLocalEndpoint")));
    }

    public yhtRegPortType getyhtRegPortTypeLocalEndpoint(String url) {
        yhtRegPortType var = getyhtRegPortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        yhtRegClient client = new yhtRegClient();
        
		//create a default service endpoint
        yhtRegPortType service = client.getyhtRegHttpPort();
        
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
