package com.soarswing.camel.job;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessInfoJob {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInfoJob.class);

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;
	
	public void deplayProcessInfo() {
		LOGGER.info("Number of process definitons: {}", repositoryService.createProcessDefinitionQuery().count());
		LOGGER.info("Number of tasks: {}", taskService.createTaskQuery().count());
		LOGGER.info("Number of tasks after process start: {}", taskService.createTaskQuery().count());
	}
}
