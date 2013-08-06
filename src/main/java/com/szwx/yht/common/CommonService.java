package com.szwx.yht.common;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Service层的基类
 * @author zhangyj
 * @date Mar 21, 2012
 */
public class CommonService {
	protected CommonDao commonDao;
	protected Common2Dao common2Dao;
	protected Map<String, Object> sessionSerive=new HashMap<String, Object>();
	public CommonDao getCommonDao() {
		return commonDao;
	}
	@Resource(name="commonDAO")
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public Common2Dao getCommon2Dao() {
		return common2Dao;
	}
	@Resource(name="common2DAO")
	public void setCommon2Dao(Common2Dao common2Dao) {
		this.common2Dao = common2Dao;
	}
	public Map<String, Object> getSessionSerive() {
		return sessionSerive;
	}
	public void setSessionSerive(Map<String, Object> sessionSerive) {
		this.sessionSerive = sessionSerive;
	}
	
}
