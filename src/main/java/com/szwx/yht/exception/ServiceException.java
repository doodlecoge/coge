package com.szwx.yht.exception;

import org.apache.log4j.Logger;

/**
 * Service 层异常处理
 * @author zhangyj
 * @date Mar 20, 2012
 */
public class ServiceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2121895172970226532L;
	private Logger log = Logger.getLogger(ServiceException.class);
	public ServiceException(String message) {
		super(message);
		log.error(message);
	}

	public ServiceException(String msg,Exception e) {
		super(msg,e);
		log.error(msg);
	}
}
