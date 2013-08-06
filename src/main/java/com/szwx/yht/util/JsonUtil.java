package com.szwx.yht.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component("jsonUtil")
public class JsonUtil {
	
	/*public JsonUtil(){
		System.out.println(12312123);
	}*/
	/**
	* 从一个JSON 对象字符格式中得到一个java对象，形如：
	* {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	* @param object
	* @param clazz
	* @return
	*/
	public  Object getDTO(String jsonString, Class clazz){
		JSONObject jsonObject = null;
		try{
			jsonObject = JSONObject.fromObject(jsonString);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	
	/**
	* 从一个JSON数组得到一个java对象数组，形如：
	* [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]
	* @param object
	* @param clazz
	* @return
	*/
	public  Object[] getDTOArray(String jsonString, Class clazz){
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for(int i = 0; i < array.size(); i++){
		JSONObject jsonObject = array.getJSONObject(i);
		obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	} 
	
	/**
	* 从一个JSON数组得到一个java对象集合
	* @param object
	* @param clazz
	* @return
	*/
	public  List getDTOList(String jsonString, Class clazz){
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for(Iterator iter = array.iterator(); iter.hasNext();){
			JSONObject jsonObject = (JSONObject)iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	} 
	/**
	* 从json HASH表达式中获取一个map，该map支持嵌套功能
	* 形如：{"id" : "johncon", "name" : "小强"}
	* 注意commons-collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
	* @param object
	* @return
	*/
	public  Map getMapFromJson(String jsonString) {
	        JSONObject jsonObject = JSONObject.fromObject(jsonString);
	        Map map = new HashMap();
	        for(Iterator iter = jsonObject.keys(); iter.hasNext();){
	            String key = (String)iter.next();
	            map.put(key, jsonObject.get(key));
	        }
	        return map;
	 } 
	/**
     * 从json数组中得到相应java数组
     * json形如：["123", "456"]
     * @param jsonString
     * @return
     */
    public  Object[] getObjectArrayFromJson(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }
    
    /**
    * 把数据对象转换成json字符串
    * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
    * 数组对象形如：[{}, {}, {}, ...]
    * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}
    * @param object
    * @return
    */
    public  String getJSONString(Object object) throws Exception{
	    String jsonString = null;
	    //日期值处理器
	    JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());//处理日期
	    if(object != null){
	    	if(object instanceof Collection || object instanceof Object[]){
	    		jsonString = JSONArray.fromObject(object, jsonConfig).toString();
	    	}else{
	    	jsonString = JSONObject.fromObject(object, jsonConfig).toString();
	    	}
	    }
	    return jsonString == null ? "{}" : jsonString;
    } 
    
/*    public static void main(String[] args)throws Exception{
    	
		Role role=new Role();
		role.setAddTime(new Date());
		role.setId(22);
		
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");  
		JsonUtil ut= (JsonUtil)factory.getBean("jsonUtil");  
		
		Role role3=new Role();
		role3.setAddTime(new Date());
		role3.setId(2233333);
		
		JsonUtil ut2= (JsonUtil)factory.getBean("jsonUtil"); 
		
		System.out.println(ut.getJSONString(role));
		System.out.println(ut2.getJSONString(role3));
	}*/
}

//ＪＳＯＮ日期处理类
class JsonDateValueProcessor implements JsonValueProcessor {

	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {

	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	} 
	private Object process( Object value, JsonConfig jsonConfig ) {
		if (value instanceof Date) {
		String str = new SimpleDateFormat(format).format((Date) value);
		return str;
		}
		return value == null ? null : value.toString();
	} 
}
