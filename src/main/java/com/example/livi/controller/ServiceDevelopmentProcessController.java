package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.ServiceDevelopmentProcess;
import com.example.livi.service.ServiceDevelopmentProcessService;

@RestController
@RequestMapping("/servicedevelopmentprocess")
public class ServiceDevelopmentProcessController {

	@Autowired
	private ServiceDevelopmentProcessService serviceDevelopmentProcessService;
	
	@GetMapping("")
	public List<ServiceDevelopmentProcess> getServiceDevelopmentProcesses() {
		return serviceDevelopmentProcessService.getServiceDevelopmentProcesses();
	}
	
	@GetMapping("{id}")
	public ServiceDevelopmentProcess getServiceDevelopmentProcessById(@PathVariable int id) {
		return serviceDevelopmentProcessService.getServiceDevelopmentProcessById(id);
	}
	
	@PostMapping("{sessionId}")
	public ServiceDevelopmentProcess addServiceDevelopmentProcess(@RequestBody ServiceDevelopmentProcess serviceDevelopmentProcess, @PathVariable int sessionId) {
		//TODO: process POST request
		return serviceDevelopmentProcessService.addServiceDevelopmentProcess(serviceDevelopmentProcess, sessionId);
	}
	
	@PutMapping("/{id}")
	public ServiceDevelopmentProcess updateServiceDevelopmentProcess(@PathVariable int id, @RequestBody ServiceDevelopmentProcess serviceDevelopmentProcess) {
		return serviceDevelopmentProcessService.updateServiceDevelopmentProcess(id, serviceDevelopmentProcess);
	}
	
	@DeleteMapping("/{id}")
	public void deleteServiceDevelopmentProcess(@PathVariable int id) {
		serviceDevelopmentProcessService.deleteServiceDevelopmentProcess(id);
	}
}
