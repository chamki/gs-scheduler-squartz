package com.soarswing.camel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YourService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(YourService.class);

	
	@Autowired
	private MyService myService;
	
	public void sayHello() {
		myService.sayHello();
	}
	
	public void sellBook() {
		LOGGER.info("Your are selling books!");
	}
}
