package com.soarswing.camel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DavidService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DavidService.class);
	
	public void draw() {
		LOGGER.info("Dravid is drawing.");
	}
}
