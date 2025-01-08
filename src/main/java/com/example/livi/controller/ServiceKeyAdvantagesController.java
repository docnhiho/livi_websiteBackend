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

import com.example.livi.model.ServiceKeyAdvantages;
import com.example.livi.service.ServiceKeyAdvantagesService;

@RestController
@RequestMapping("/servicekeyadvantages")
public class ServiceKeyAdvantagesController {

	@Autowired
	private ServiceKeyAdvantagesService serviceKeyAdvantagesService;
	
	@GetMapping("")
	public List<ServiceKeyAdvantages> getAllSessions() {
		return serviceKeyAdvantagesService.getServiceKeyAdvantages();
	}
	
	@GetMapping("{id}")
	public ServiceKeyAdvantages getServiceKeyAdvantages(@PathVariable int id) {
		return serviceKeyAdvantagesService.getServiceKeyAdvantagesById(id);
	}
	
	@PostMapping("{sessionId}")
	public ServiceKeyAdvantages addServiceKeyAdvantages(@RequestBody ServiceKeyAdvantages serviceKeyAdvantages, @PathVariable int sessionId) {
		//TODO: process POST request
		return serviceKeyAdvantagesService.addServiceKeyAdvantages(serviceKeyAdvantages, sessionId);
	}
	
	@PutMapping("/{id}")
	public ServiceKeyAdvantages upAdvantages(@PathVariable int id, @RequestBody ServiceKeyAdvantages serviceKeyAdvantages) {
		return serviceKeyAdvantagesService.updateServiceKeyAdvantages(id, serviceKeyAdvantages);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		serviceKeyAdvantagesService.deleteServiceKeyAdvantages(id);
	}
}
