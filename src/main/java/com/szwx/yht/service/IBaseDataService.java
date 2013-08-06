package com.szwx.yht.service;

import com.szwx.yht.exception.ServiceException;

import java.util.List;

/**
 * 静态数据处理接口
 * @author zhangyj
 * @date Jun 30, 2012
 */
public interface IBaseDataService {
	/**
	 * 根据上级数据获取子级数据
	 * @author zhangyj
	 * @date Jun 30, 2012
	 * @param parentData  上级数据
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public List<String> getBaseData(String parentData)throws ServiceException;
}
