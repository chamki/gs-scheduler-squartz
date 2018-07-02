package com.soarswing.camel.job;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.soarswing.camel.service.MyService;

//@Configuration
@Component
//@EnableScheduling // 该注解必须要加
public class ScheduleTask {
	
	@Autowired
	private MyService myService;

	public void scheduleTest() {
		System.err.println("scheduleTest开始定时执行");
	}

	public void sayHello() {
		myService.sayHello();
	}
}
