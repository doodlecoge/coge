package com.szwx.yht.common;

import com.szwx.yht.exception.ServiceException;
import com.szwx.yht.service.IBaseDataService;
import com.szwx.yht.service.impl.BaseDataServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据院贮入内存
 * @author zhangyj
 * @date Jun 30, 2012
 */
public class BaseDataQuery {
	private static BaseDataQuery baseDataQuery=null;
	private static Map<String, List> baseData=null;
	
	private BaseDataQuery(){
		
	}
	/**
	 * 单例
	 * @author zhangyj
	 * @date Jun 30, 2012
	 * @return
	 */
	public static BaseDataQuery newInstance(){
		if(null==baseDataQuery){
			return new BaseDataQuery();
		}
		return baseDataQuery;
	}
/*	*//**
	 * 获取静态数据
	 * @author zhangyj
	 * @date Jun 30, 2012
	 * @param parent 上级数据
	 * @return
	 * @throws Exception
	 *//*
	public List<String> getBaseData(String parent)throws ServiceException{
		if(null==baseData){
			baseData=new HashMap<String, List>();
		}
		if(baseData.containsKey(parent)){
			return baseData.get(parent);
		}else {
			List<String> list=null;
			list = dataService.getBaseData(parent);
			baseData.put(parent,list);
			return  list;
		}
	}*/
	/**
	 * 获取静态数据
	 * @author zhangyj
	 * @date Jun 30, 2012
	 * @param parent 上级数据
	 * @return
	 * @throws Exception
	 */
	public List<String> getBaseData(String parent)throws ServiceException {
		if(null==baseData){
			baseData=new HashMap<String, List>();
		}
		if(baseData.containsKey(parent)){
			return baseData.get(parent);
		}else {
			List<String> list=null;
//			BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");  
//			dataService = (IBaseDataService)factory.getBean("baseDataService");   
			IBaseDataService dataService=new BaseDataServiceImpl();
			list = dataService.getBaseData(parent);
			baseData.put(parent,list);
			return  list;
		}
	}
}
