package com.soarswing.camel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soarswing.camel.service.MyService;
import com.soarswing.camel.service.YourService;

@RestController
public class HelloController {

	@Autowired
	private MyService myServie;
	
	@Autowired
	private YourService yourService;

	@GetMapping("/hello")
	public String greeting() {
		myServie.sayHello();
		return "hello";
	}
	
	@GetMapping("/hi")
	public String greet() {
		yourService.sayHello();
		return "hi";
	}
}
