package com.szwx.yht.util;

import com.szwx.yht.bean.User;
import com.szwx.yht.job.ResetUserTask;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 重置工具类
 * @author zhangyj
 * @date Mar 22, 2012
 */
public class RestUtil {
	private RestUtil(){

	}
	private static RestUtil restUtil=null;
	
	public static RestUtil newInstance(){
		if(null==restUtil){
			restUtil= new RestUtil();
		}
		
		return restUtil;
	}
	/**
	 * minute分钟后对用户错误登陆次数清零
	 * @author zhangyj
	 * @date Mar 22, 2012
	 */
	public void clearZero(User user,int minute){
		Timer timer=new Timer();
		TimerTask task=new ResetUserTask(user);
		timer.schedule(task, minute*60*1000);
	}
	
}
