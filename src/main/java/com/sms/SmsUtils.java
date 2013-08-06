package com.sms;

import com.hch.security.Config;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class SmsUtils {
    public static SmsResponse unmarshalResponse(String xml) throws JAXBException {
        return unmarshalResponse(new ByteArrayInputStream(xml.getBytes()));
    }

    public static SmsResponse unmarshalResponse(InputStream is) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SmsResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (SmsResponse) unmarshaller.unmarshal(is);
    }

    public static String marshalRequest(SmsRequest req) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(SmsRequest.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "GB18030");

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(req, sw);
        return sw.toString();
    }

    public static SmsResponse sendShortMessage(SmsRequest req) throws JAXBException, IOException {
        HttpClient client = new DefaultHttpClient();

//        HttpHost proxy = new HttpHost("172.25.220.130", 8080);
//        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

//        HttpHost host = new HttpHost(Config.SmsIp, Config.SmsPort);
        HttpHost host = new HttpHost(Config.getString("SmsIp"), Config.getInt("SmsPort"));
        HttpPost post = new HttpPost("/");

        String msg = marshalRequest(req);
        ByteArrayEntity entity = new ByteArrayEntity(msg.getBytes("gb18030"));
        post.setEntity(entity);

        HttpResponse resp = client.execute(host, post);

        InputStream is = resp.getEntity().getContent();
        return unmarshalResponse(is);
    }
}
