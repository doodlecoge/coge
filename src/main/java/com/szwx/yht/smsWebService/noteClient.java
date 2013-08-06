
package com.szwx.yht.smsWebService;

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

public class noteClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public noteClient() {
        create0();
        Endpoint notePortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://webService", "notePortTypeLocalEndpoint"), new QName("http://webService", "notePortTypeLocalBinding"), "xfire.local://note");
        endpoints.put(new QName("http://webService", "notePortTypeLocalEndpoint"), notePortTypeLocalEndpointEP);
        Endpoint noteHttpPortEP = service0 .addEndpoint(new QName("http://webService", "noteHttpPort"), new QName("http://webService", "noteHttpBinding"), "http://172.21.1.22:8080/NOTE/services/note");//http://172.21.1.22:8080/NOTE/services/note;//http://120.195.10.35:36210/NOTE/services/note
        endpoints.put(new QName("http://webService", "noteHttpPort"), noteHttpPortEP);
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
        service0 = asf.create((com.szwx.yht.smsWebService.notePortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://webService", "noteHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://webService", "notePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public notePortType getnotePortTypeLocalEndpoint() {
        return ((notePortType)(this).getEndpoint(new QName("http://webService", "notePortTypeLocalEndpoint")));
    }

    public notePortType getnotePortTypeLocalEndpoint(String url) {
        notePortType var = getnotePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public notePortType getnoteHttpPort() {
        return ((notePortType)(this).getEndpoint(new QName("http://webService", "noteHttpPort")));
    }

    public notePortType getnoteHttpPort(String url) {
        notePortType var = getnoteHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        noteClient client = new noteClient();
        
		//create a default service endpoint
        notePortType service = client.getnoteHttpPort();
        
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
