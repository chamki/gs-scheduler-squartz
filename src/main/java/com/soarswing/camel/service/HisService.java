package com.soarswing.camel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HisService {

	private static Logger LOGGER = LoggerFactory.getLogger(HisService.class);
	
	public void sellPhone() {
		LOGGER.info("He is selling phone.");
	}
}
