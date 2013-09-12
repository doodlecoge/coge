package com.szwx.yht;

import com.szwx.yht.net.HttpEngine;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-7-2
 * Time: 下午10:25
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        String str = StringUtils.escape("您在12320网上预约挂号的手机验证码为{code}，如果不是您本人请忽略此消息。");
//        System.out.println(str);
//
//        List<String> lst = new ArrayList<String>();
//
//        lst.add("a");
//        lst.add("b");
//        lst.add("c");
//
//        int len = lst.size();
//        for (int i = 0; i < len; i++) {
//
//            System.out.println(lst.get(i));
//            lst.remove(i);
//        }


//        HttpEngine eng = new HttpEngine();
//        eng.get("http://www.jssz12320.cn:8080/hrs/index");
//        int statusCode = eng.getStatusCode();
//        System.out.println(statusCode);
//
//        System.out.println(eng.getHtml());


        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpHost proxy = new HttpHost("172.25.220.130", 8080, "http");

        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//
//        httpClient.getHostConfiguration().setProxy("192.168.101.1", 5608);
////使用抢先认证
//        httpClient.getParams().setAuthenticationPreemptive(true);


        HttpGet get = new HttpGet("http://www.jssz12320.cn:8080/hrs/async/queryQueuingPosition");

        HttpResponse resp = httpClient.execute(get);

        InputStream is = resp.getEntity().getContent();


        IOUtils.copy(is, System.out);


    }
}
