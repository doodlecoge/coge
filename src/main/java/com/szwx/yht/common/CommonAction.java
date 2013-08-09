package com.szwx.yht.common;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.szwx.yht.dto.MethodCallBean;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有Action类的基类
 * @author zhangyj
 * @date Mar 20, 2012
 */
public  class CommonAction extends ActionSupport
        implements SessionAware,RequestAware,Preparable {

    //hch 2012/06/27
    CommonDao commonDao;

    public CommonDao getCommonDao() {
        return commonDao;
    }

    @Resource(name="commonDAO")
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = -5840453106760961744L;
	
	protected Map<String, Object> request=new HashMap<String, Object>();
	protected Map<String, Object> session=new HashMap<String, Object>();
	@SuppressWarnings("unchecked")
	protected MethodCallBean call;//返回结果集合（异步）
	protected static final String GLOBAL_JSON = "globalJson";			//全局
	protected static final String CALL_JSON = "callJson";				//call方式
	protected Map<String,Object> jsonValue = new HashMap<String,Object>();//全局方式
	protected HttpServletResponse response;
	protected OutputStream out;
	protected Logger log = Logger.getLogger(CommonAction.class);

	public Map<String, Object> getJsonValue() {
		return jsonValue;
	}

	public void setJsonValue(Map<String, Object> jsonValue) {
		this.jsonValue = jsonValue;
	}

	@SuppressWarnings("unchecked")
	public MethodCallBean getCall() {
		return call;
	}

	@SuppressWarnings("unchecked")
	public void setCall(MethodCallBean call) {
		this.call = call;
	}
	public void prepare() throws Exception {
		clearErrorsAndMessages();
	}
	
	
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
