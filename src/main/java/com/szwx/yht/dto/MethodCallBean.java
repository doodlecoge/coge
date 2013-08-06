package com.szwx.yht.dto;

import java.util.Collection;


/**
 * 方法调用结果属性Bean
 * @author zhangyj
 * @date Mar 20, 2012
 * @param <T>
 */
public class MethodCallBean<T>{

	/**
	 * 是否成功
	 */
	private boolean isSuccess=false;
	/**
	 * 方法处理的成功与失败消息
	 */
	private String msg;
	/**
	 * 返回的对象T
	 */
	private T object;
	/**
	 * Constructor
	 */
	private Collection<T> objects;
	/**
	 * 状态
	 */
	private long state=0;
	
	public MethodCallBean() {
		
	}
	
	public MethodCallBean(boolean isSuccess, String msg) {
		this.isSuccess = isSuccess;
		this.msg = msg;
	}
	
	public MethodCallBean(boolean isSuccess, String msg, T object) {
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.object = object;
	}
	public MethodCallBean(boolean isSuccess, String msg, Collection<T> objects) {
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.objects = objects;
	}
	public MethodCallBean(String msg) {
		this.isSuccess = false;
		this.msg = msg;
		this.object = null;
	}
	
	public void setSuccessInfo(String msg,T object){
		this.isSuccess = true;
		this.msg = msg;
		this.object = object;
	}
	public void setSuccessInfo(String msg,Collection<T> objects){
		this.isSuccess = true;
		this.msg = msg;
		this.objects = objects;
	}
	public void setMsg(boolean isSuccess,String msg){
		this.isSuccess = isSuccess;
		this.msg = msg;
	}
	
	public void setSuccessInfo(T userObject){
		setSuccessInfo("操作成功",userObject);
	}
	public void setSuccessInfo(Collection<T> objects){
		setSuccessInfo("操作成功",objects);
	}
	
	
	/**
	 * 成功时的简化构造
	 * @param userObject
	 */
	public MethodCallBean(T userObject) {
		this.isSuccess = true;
		this.msg = "";
		this.object = userObject;
	}
	public MethodCallBean(Collection<T> objects) {
		this.isSuccess = true;
		this.msg = "";
		this.objects = objects;
	}
	

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public Collection<T> getObjects() {
		return objects;
	}

	public void setObjects(Collection<T> objects) {
		this.objects = objects;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

}
