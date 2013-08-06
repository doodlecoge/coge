package com.szwx.yht.util;

import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

import java.net.MalformedURLException;
import java.net.URL;

public class SendMsgService {
	//短信
//	private static String url = "http://120.195.10.34:8881/services/customerSMS?wsdl";//老接口
	private static String url ="http://172.21.1.24:8089/services/customerSMS?wsdl";//新接口，正式内网
//	private static String url ="http://192.168.0.24:8089/services/customerSMS?wsdl";//新接口，测试内网
//	private static String url ="http://sms.szpay.net:8881/services/customerSMS?wsdl";//新接口，外网
	private static Logger log = Logger.getLogger(SendMsgService.class);
	
	public static boolean sendMsg(String phone,String content) {
		String digest = Md5.getMD5Str("yht"+Md5.getMD5Str("yht"));
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<request>" +
				"<header>" +
				"<userName>yht</userName>" +
				"<digest>"+digest+"</digest>" +
				"</header>" +
				"<body>" +
				"<destPhone>"+phone+"</destPhone>" +
				"<content>"+content+"</content>" +
				"<priority>2</priority>" +
				"</body>" +
				"</request>";
		Client c = null;
		String responseStr = "";
		try {
			c = new Client(new URL(url));
			Object[] requestobj = new Object[1];
			requestobj[0] = xml;
			Object[] results = c.invoke("send", requestobj);
			if (results == null) {
				return false;
			} else {
				responseStr = results[0].toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("短信返回="+responseStr);
		log.info("phone:"+phone+"------context:"+content+"-----短信返回="+responseStr);
		if (responseStr.indexOf("0000") > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//老的
//	public static boolean sendMsg(String phone,String content) {
//		try {
//			noteClient client = new noteClient();
//			notePortType service = client.getnoteHttpPort();
//			String reqTime = TimeUtil.formatDate1(new Date(), "yyyyMMddhhmmss");
//			String sign = Md5.getMD5Str("zhyl"+reqTime+Md5.getMD5Str("111111"));
//			try {
//				return service.getSendMessageUSER2(reqTime, sign, content, phone, 2, "noteSms", "zhyl");
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				return false;
//			}
//		} catch(Exception ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
	
	public static void main(String[] args) {
		System.out.println(SendMsgService.sendMsg("18662484771", "一号通短信测试！！！"));
	}
}
