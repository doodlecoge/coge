package com.szwx.yht.job;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.szwx.yht.common.QuartzService;

public class intervalTimeUpdateWorkTimeInfo implements Job{

	public void execute(JobExecutionContext szwx) throws JobExecutionException {
		Map dataMap = szwx.getJobDetail().getJobDataMap();
		ApplicationContext ctx = (ApplicationContext)dataMap.get("applicationContext");
		QuartzService qs=(QuartzService)ctx.getBean("quartzService");
		qs.updateWorkTimeInfo();
	}

}
