package com.szwx.yht.common;

import com.szwx.yht.service.IRegisterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时器
 * @author Administrator
 * @date 2012-10-17 下午01:51:45
 *
 */
@Component("quartzService")
public class QuartzService {
	
	private Logger log= Logger.getLogger(QuartzService.class);

	@Autowired
	private IRegisterService registerService;
	

	
	public void updateWorkTimeInfo(){
		log.fatal("更新排班数据开始……");
		registerService.updateWorkTimeInfo();
		log.fatal("更新排班数据结束……");
	}

}
