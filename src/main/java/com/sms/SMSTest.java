package com.sms;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-13
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
public class SMSTest {
    public static void main(String[] args) throws JAXBException, IOException {
        SmsRequest req = new SmsRequest();
        req.header = new SmsRequestHeader("XXZX", "XXZX2013");
        req.body = new SmsRequestBody("13862020380", "你好怀超");

        String body = SmsUtils.marshalRequest(req);
        System.out.println(body);

        ByteArrayEntity entity = new ByteArrayEntity(body.getBytes("gb18030"));

        HttpClient client = new DefaultHttpClient();


        HttpHost proxy = new HttpHost("172.25.220.130", 8080);
        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        HttpHost host = new HttpHost("192.168.10.78", 8900);
        HttpPost post = new HttpPost("/");
        post.setEntity(entity);

        HttpResponse resp = client.execute(host, post);

        InputStream is = resp.getEntity().getContent();

        IOUtils.copy(is, System.out);


    }


}
