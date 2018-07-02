package com.soarswing.camel.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soarswing.camel.service.YourService;

@Component
public class YourJob {
	
	@Autowired
	private YourService yourService;
	
	public void sellBook() {
		yourService.sellBook();
	}

}
