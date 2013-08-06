package com.szwx.yht.service;

import com.szwx.yht.bean.SoftPhone;
import com.szwx.yht.dto.MethodCallBean;
import com.szwx.yht.exception.ServiceException;

public interface ISoftPhoneService {
	/**
	 * 更新
	 * @author zhangyj
	 * @date Jul 6, 2012
	 * @param softPhone
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public MethodCallBean updateSoftPhone(SoftPhone softPhone)throws ServiceException;
	/**
	 *
	 * @param softPhone
	 * @return
	 * @throws com.szwx.yht.exception.ServiceException
	 */
	public SoftPhone getSoftPhone(String phoneNo)throws ServiceException;
}
