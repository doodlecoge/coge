package com.szwx.yht.service;

import com.szwx.yht.bean.SYSLog;
import com.szwx.yht.exception.ServiceException;

/**
 * 系统日志接口
 * @author zhangyj
 * @date Mar 19, 2012
 */
public interface ISysLogService {
	/**
	 * 添加一条日志记录
	 * @author zhangyj
	 * @date Mar 21, 2012
	 */
	public void addLog(SYSLog log)throws ServiceException;
	/**
	 * 按ID获取日志
	 * @author zhangyj
	 * @date Mar 28, 2012
	 * @param id	日志ID
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public SYSLog getLogById(int id)throws ServiceException;
}
