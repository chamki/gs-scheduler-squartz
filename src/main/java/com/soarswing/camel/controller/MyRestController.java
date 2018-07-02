package com.soarswing.camel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soarswing.camel.model.Person;
import com.soarswing.camel.service.MyService;

@RestController
public class MyRestController {

	@Autowired
	private MyService myService;
	
	@PostMapping("/process")
	public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
		Map<String, Object> data = new HashMap<String, Object>();
		ProcessInstance processInstance = myService.startProcess(startProcessRepresentation.getProcessDefinitionKey(),startProcessRepresentation.getAssignee());
		data.put("code", 0);
		data.put("msg", "success");
		data.put("process", processInstance);
	}
	
	@RequestMapping(value="/tasks/{assigne}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TaskRepresentation> getTask(@PathVariable(name="assigne", required=false)String assigne) {
		List<Task> tasks = myService.getTasks(assigne, true);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for(Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName()));
		}
		return dtos;
	}
	
	@GetMapping("/complete/{taskId}/{username}")
	public Map<String, Object> completeTask(@PathVariable(name="taskId", required=true)String taskId, @PathVariable(name="username", required=false)String username) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> variables = new HashMap<String, Object>();
		Person person = myService.findByUsername(username);
		variables.put("person", person);
		myService.completeTask(taskId, variables);
		data.put("complete", true);
		return data;
	}
	
	@GetMapping("/history")
	public List<HistoricProcessInstance> getHistory(@RequestParam String processDefinitionName) {
		List<HistoricProcessInstance> list = myService.history(processDefinitionName);
		return list;
	}
	
	static class TaskRepresentation {
		private String id;
		
		private String name;

		public TaskRepresentation(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
	
	static class StartProcessRepresentation {
		
		private String processDefinitionKey;
		
		public String getProcessDefinitionKey() {
			return processDefinitionKey;
		}

		public void setProcessDefinitionKey(String processDefinitionKey) {
			this.processDefinitionKey = processDefinitionKey;
		}

		private String assignee;

		public String getAssignee() {
			return assignee;
		}

		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}
		
		
	}
}
