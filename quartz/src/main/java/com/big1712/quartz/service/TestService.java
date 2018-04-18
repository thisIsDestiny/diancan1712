package com.big1712.quartz.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestService extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


		//利用上下文对象,获取能够操作order表格的对象OrderMapper
		ApplicationContext applicationContext=(ApplicationContext) 
				context.getJobDetail().getJobDataMap().
				get("applicationContext");
	
		//定时任务操作
		System.out.println("测试定时任务："+System.currentTimeMillis());
		
		
	}

}
