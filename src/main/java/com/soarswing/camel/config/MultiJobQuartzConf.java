package com.soarswing.camel.config;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.soarswing.camel.service.DavidService;
import com.soarswing.camel.service.JerryService;
import com.soarswing.camel.service.TomService;

//@Configuration
public class MultiJobQuartzConf {
	
	@Autowired
	private TomService tomService;
	
	@Autowired
	private JerryService jerryService;
	
	@Autowired
	private DavidService davidService;

	@Bean
	public JobDetailFactoryBean defineTomJob() {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setName("tomJob");
		jobDetail.setGroup("artGroup");
		jobDetail.setJobClass(com.soarswing.camel.job.TomJob.class);
		Map<String, Object> jobDataAsMap = new HashMap<String, Object>();
		jobDataAsMap.put("tomService", tomService);
		jobDetail.setJobDataAsMap(jobDataAsMap);
		return jobDetail;
	}
	
	@Bean
	public JobDetailFactoryBean defineJerryJob() {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setName("jerryJob");
		jobDetail.setGroup("artGroup");
		jobDetail.setJobClass(com.soarswing.camel.job.JerryJob.class);
		Map<String, Object> jobDataAsMap = new HashMap<String, Object>();
		jobDataAsMap.put("jerryService", jerryService);
		jobDetail.setJobDataAsMap(jobDataAsMap);
		return jobDetail;
	}
	
	@Bean
	public JobDetailFactoryBean defineDavidJob() {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setName("davidJob");
		jobDetail.setGroup("artGroup");
		jobDetail.setJobClass(com.soarswing.camel.job.DavidJob.class);
		Map<String, Object> jobDataAsMap = new HashMap<String, Object>();
		jobDataAsMap.put("davidService", davidService);
		jobDetail.setJobDataAsMap(jobDataAsMap);
		return jobDetail;
	}
	
	@Bean(name="tomTrigger")
	public CronTriggerFactoryBean tomTrigger() {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(defineTomJob().getObject());
		trigger.setCronExpression("0 * * * * ?");
		trigger.setName("tomJobTrigger");
		return trigger;
	}
	
	@Bean(name="jerryTrigger")
	public CronTriggerFactoryBean jerryTrigger() {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(defineJerryJob().getObject());
		trigger.setCronExpression("0 * * * * ?");
		trigger.setName("jerryJobTrigger");
		return trigger;
	}
	
	@Bean(name="davidTrigger")
	public CronTriggerFactoryBean davidTrigger() {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(defineDavidJob().getObject());
		trigger.setCronExpression("0 * * * * ?");
		trigger.setName("davidJobTrigger");
		return trigger;
	}
	
	@Bean
	public SchedulerFactoryBean startUpScheduler(Trigger tomTrigger, Trigger jerryTrigger, Trigger davidTrigger) {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setOverwriteExistingJobs(true);
		scheduler.setStartupDelay(5);
		scheduler.setTriggers(tomTrigger, jerryTrigger, davidTrigger);
		scheduler.setBeanName("employeeScheduler");
		return scheduler;
	}
}
