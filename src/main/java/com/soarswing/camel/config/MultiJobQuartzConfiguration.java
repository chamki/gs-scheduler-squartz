package com.soarswing.camel.config;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.soarswing.camel.job.HisJob;
import com.soarswing.camel.job.MyJob;
import com.soarswing.camel.job.YourJob;

//@Configuration
public class MultiJobQuartzConfiguration {

	//@Bean("mySellJob")
	//@Bean
	public MethodInvokingJobDetailFactoryBean startUpMyJob(MyJob myJob) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		jobDetail.setConcurrent(true);
		jobDetail.setName("mySellingCarJob");
		jobDetail.setGroup("sellGroup");
		jobDetail.setTargetObject(myJob);
		jobDetail.setTargetMethod("sellCar");
		return jobDetail;
	}
	
	//@Bean("yourSellJob")
	//@Bean
	public MethodInvokingJobDetailFactoryBean startUpYourJob(YourJob yourJob) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		jobDetail.setConcurrent(true);
		jobDetail.setName("yourSellingBookJob");
		jobDetail.setGroup("sellGroup");
		jobDetail.setTargetObject(yourJob);
		jobDetail.setTargetMethod("sellBook");
		return jobDetail;
	}
	
	//@Bean("hisSellJob")
	//@Bean
	public MethodInvokingJobDetailFactoryBean startUpHisJob(HisJob hisJob) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		jobDetail.setConcurrent(true);
		jobDetail.setName("hisSellingPhoneJob");
		jobDetail.setGroup("sellGroup");
		jobDetail.setTargetObject(hisJob);
		jobDetail.setTargetMethod("sellPhone");
		return jobDetail;
	}
	
	//@Bean("myTrigger")
	public CronTriggerFactoryBean myJobTrigger(MyJob myJob) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		MethodInvokingJobDetailFactoryBean mySellJob = startUpMyJob(myJob);
		trigger.setJobDetail(mySellJob.getObject());
		trigger.setCronExpression("0/5 * * * * ?");
		trigger.setName("myJobTrigger");
		return trigger;
	}
	
	//@Bean("yourTrigger")
	public CronTriggerFactoryBean yourJobTrigger(YourJob yourJob) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		MethodInvokingJobDetailFactoryBean yourSellJob = startUpYourJob(yourJob);
		trigger.setJobDetail(yourSellJob.getObject());
		trigger.setCronExpression("0/10 * * * * ?");
		trigger.setName("yourJobTrigger");
		return trigger;
	}
	
	//@Bean("hisTrigger")
	public CronTriggerFactoryBean hisJobTrigger(HisJob hisJob) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		MethodInvokingJobDetailFactoryBean hisSellJob = startUpHisJob(hisJob);
		trigger.setJobDetail(hisSellJob.getObject());
		trigger.setCronExpression("0/15 * * * * ?");
		trigger.setName("hisJobTrigger");
		return trigger;
	}
	
	//@Bean
	public SchedulerFactoryBean startUpAllJobs(Trigger myTrigger, Trigger yourTrigger, Trigger hisTrigger) {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setOverwriteExistingJobs(true);
		scheduler.setStartupDelay(5);
		scheduler.setTriggers(myTrigger, yourTrigger, hisTrigger);
		return scheduler;
		
	}
}
