package com.big1712.quartz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:applicationContext-scheduler.xml")
/**
 * 加载定时任务的配置文件
 * 
 * @author Administrator
 *
 */
public class QuartzConfig {

}
