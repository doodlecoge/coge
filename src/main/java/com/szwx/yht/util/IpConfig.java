package com.szwx.yht.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author zhangyj
 * @date Mar 21, 2012
 */
public class IpConfig {
	/**
	 * 获取IP地址
	 * @author zhangyj
	 * @date Mar 21, 2012
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("Proxy-Client-IP");    
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("WL-Proxy-Client-IP");    
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getRemoteAddr();    
	    }    
	    return ip;    
	}
}
