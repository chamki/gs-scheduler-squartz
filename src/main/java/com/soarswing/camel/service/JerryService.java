package com.soarswing.camel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JerryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JerryService.class);
	
	public void writing() {
		LOGGER.info("Jerry is writting.");
	}
}
