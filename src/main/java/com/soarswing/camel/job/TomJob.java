package com.soarswing.camel.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.soarswing.camel.service.TomService;

public class TomJob extends QuartzJobBean {
	
	private TomService tomService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		tomService.sing();

	}

	public void setTomService(TomService tomService) {
		this.tomService = tomService;
	}

}
