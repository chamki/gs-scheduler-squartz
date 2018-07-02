package com.soarswing.camel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TomService.class);
	
	public void sing() {
		LOGGER.info("Tom is singing.");
	}
}
