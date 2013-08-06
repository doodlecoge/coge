package com.szwx.yht.service.impl;

import com.szwx.yht.bean.SYSLog;
import com.szwx.yht.common.CommonService;
import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.ISysLogService;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class SysLogServiceImpl extends CommonService implements ISysLogService {

	public void addLog(SYSLog log) throws ServiceException {
		try {
			commonDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("SysLogServiceImpl.addLog系统异常："+e.getMessage(),e);
		}
		
	}


	public SYSLog getLogById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
