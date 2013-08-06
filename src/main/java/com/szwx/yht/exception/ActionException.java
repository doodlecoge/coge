package com.szwx.yht.exception;

import org.apache.log4j.Logger;

/**
 * Action层异常处理
 * @author zhangyj
 * @date Mar 20, 2012
 */
public class ActionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6385237040944845573L;
	private Logger log = Logger.getLogger(ActionException.class);

	public ActionException(String message) {
		super(message);
		log.error(message);
	}

	public ActionException(String msg,Exception e) {
		super(msg,e);
		log.error(msg);
	}
}
