package com.soarswing.camel.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.soarswing.camel.service.DavidService;

public class DavidJob extends QuartzJobBean {

	private DavidService davidService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		davidService.draw();

	}

	public void setDavidService(DavidService davidService) {
		this.davidService = davidService;
	}

}
