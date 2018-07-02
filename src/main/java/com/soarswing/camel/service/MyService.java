package com.soarswing.camel.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soarswing.camel.model.Person;
import com.soarswing.camel.repository.PersonRepository;

@Service
@Transactional
public class MyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private IdentityService identityService;
	
	public void sayHello() {
		LOGGER.info("Hello from MyService.");
		//System.out.println("Hello, Quartz");
	}
	
	public void sellCar() {
		LOGGER.info("I am selling Car.");
	}
	
	public ProcessInstance startProcess(String processDefinitionKey, String assignee) {
		Map<String, Object> variables = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(assignee)) {
			Person person = personRepository.findByUsername(assignee);
			variables.put("person", person);
		}
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
		return processInstance;
	}
	
	public List<Task> getTasks(String assigne, Boolean group) {
		List<Task> tasks = null;
		if(StringUtils.isBlank(assigne)) {
			tasks = taskService.createTaskQuery().list();
		} else if(group == true){
			tasks = taskService.createTaskQuery().taskCandidateGroup(assigne).list();
		} else {
			tasks = taskService.createTaskQuery().taskAssignee(assigne).list();
		}
		return tasks;
	}
	
	public void completeTask(String taskId, Map<String, Object> variables) {
		Person person = (Person)variables.get("person");
		//taskService.claim(taskId, person.getUsername());
		taskService.complete(taskId);
	}
	
	public List<HistoricProcessInstance> history(String processsDifinitionName) {
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().finishedBefore(new Date()).list();
		return list;
	}
	
	public void createDemoUsers() {
		if(personRepository.findAll().size() == 0) {
			personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
			personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
		}
	}
	
	public Person findByUsername(String username) {
		Person person = personRepository.findByUsername(username);
		return person;
	}
}
