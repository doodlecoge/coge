package com.szwx.yht.exception;

import org.apache.log4j.Logger;

/**
 * DAO层异常处理
 * @author zhangyj
 * @date Mar 20, 2012
 */
public class DaoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6543858586598262442L;
	private Logger log = Logger.getLogger(DaoException.class);

	public DaoException(String message) {
		super(message);
		log.error(message);
	}

	public DaoException(String msg, Exception e) {
		super(msg,e);
		log.error(msg);
	}
}
