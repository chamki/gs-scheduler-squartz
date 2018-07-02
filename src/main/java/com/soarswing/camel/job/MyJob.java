package com.soarswing.camel.job;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soarswing.camel.service.MyService;

@Component
public class MyJob {
	
	@Autowired
	private MyService myService;

	public void sellCar() {
		myService.sellCar();
	}
	
}
