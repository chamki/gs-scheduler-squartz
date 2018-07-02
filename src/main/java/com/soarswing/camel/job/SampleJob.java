package com.soarswing.camel.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.soarswing.camel.service.MyService;

public class SampleJob extends QuartzJobBean {
	

	private MyService myService ;
	
	private Integer timeout;
	
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		myService.sayHello();
	}


	public MyService getMyService() {
		return myService;
	}


	public void setMyService(MyService myService) {
		this.myService = myService;
	}


	public Integer getTimeout() {
		return timeout;
	}


}
