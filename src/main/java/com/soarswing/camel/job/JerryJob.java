package com.soarswing.camel.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.soarswing.camel.service.JerryService;

public class JerryJob extends QuartzJobBean {
	
	private JerryService jerryService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		jerryService.writing();

	}

	public void setJerryService(JerryService jerryService) {
		this.jerryService = jerryService;
	}

}
