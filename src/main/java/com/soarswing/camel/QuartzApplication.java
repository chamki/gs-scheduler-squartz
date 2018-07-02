package com.soarswing.camel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.soarswing.camel.service.MyService;

@SpringBootApplication
public class QuartzApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(final MyService myService) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				//myService.createDemoUsers();
			}
		};
	}
}
