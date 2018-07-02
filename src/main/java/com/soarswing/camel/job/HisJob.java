package com.soarswing.camel.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soarswing.camel.service.HisService;

@Component
public class HisJob {
	
	@Autowired
	private HisService hisService;
	
	public void sellPhone() {
		hisService.sellPhone();
	}

}
