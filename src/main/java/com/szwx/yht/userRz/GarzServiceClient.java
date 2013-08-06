
package com.szwx.yht.userRz;

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

public class GarzServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public GarzServiceClient() {
        create0();
        Endpoint GarzServiceHttpPortEP = service0 .addEndpoint(new QName("http://webservice.smsf.com", "GarzServiceHttpPort"), new QName("http://webservice.smsf.com", "GarzServiceHttpBinding"), "http://172.21.1.26:8080/smsf/services/GarzService");//http://192.168.0.26:8080/smsf/services/GarzService;http://172.21.1.26:8080/smsf/services/GarzService?wsdl
        endpoints.put(new QName("http://webservice.smsf.com", "GarzServiceHttpPort"), GarzServiceHttpPortEP);
        Endpoint GarzServicePortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://webservice.smsf.com", "GarzServicePortTypeLocalEndpoint"), new QName("http://webservice.smsf.com", "GarzServicePortTypeLocalBinding"), "xfire.local://GarzService");
        endpoints.put(new QName("http://webservice.smsf.com", "GarzServicePortTypeLocalEndpoint"), GarzServicePortTypeLocalEndpointEP);
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
        service0 = asf.create((com.szwx.yht.userRz.GarzServicePortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://webservice.smsf.com", "GarzServiceHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://webservice.smsf.com", "GarzServicePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public GarzServicePortType getGarzServiceHttpPort() {
        return ((GarzServicePortType)(this).getEndpoint(new QName("http://webservice.smsf.com", "GarzServiceHttpPort")));
    }

    public GarzServicePortType getGarzServiceHttpPort(String url) {
        GarzServicePortType var = getGarzServiceHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public GarzServicePortType getGarzServicePortTypeLocalEndpoint() {
        return ((GarzServicePortType)(this).getEndpoint(new QName("http://webservice.smsf.com", "GarzServicePortTypeLocalEndpoint")));
    }

    public GarzServicePortType getGarzServicePortTypeLocalEndpoint(String url) {
        GarzServicePortType var = getGarzServicePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        GarzServiceClient client = new GarzServiceClient();
        
		//create a default service endpoint
        GarzServicePortType service = client.getGarzServiceHttpPort();
        
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
