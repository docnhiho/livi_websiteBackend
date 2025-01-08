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

import com.example.livi.model.ServiceKeyAdvantagesAplication;
import com.example.livi.service.ServiceKeyAdvantagesAplicationService;

@RestController
@RequestMapping("/servicekeyadvantagesaplication")
public class ServiceKeyAdvantagesAplicationController {
	
	@Autowired
	private ServiceKeyAdvantagesAplicationService serviceKeyAdvantagesAplicationService;
	
	@GetMapping("")
	public List<ServiceKeyAdvantagesAplication> getAdvantagesAplications() {
		return serviceKeyAdvantagesAplicationService.getServiceKeyAdvantagesAplication();
	}
	
	@GetMapping("{id}")
	public ServiceKeyAdvantagesAplication getSessionById(@PathVariable int id) {
		return serviceKeyAdvantagesAplicationService.getServiceKeyAdvantagesAplicationById(id);
	}
	
	@PostMapping("{sessionId}")
	public ServiceKeyAdvantagesAplication addSession(@RequestBody ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication, @PathVariable int sessionId) {
		//TODO: process POST request
		return serviceKeyAdvantagesAplicationService.addServiceKeyAdvantagesAplication(serviceKeyAdvantagesAplication, sessionId);
	}
	
	@PutMapping("/{id}")
	public ServiceKeyAdvantagesAplication updateSession(@PathVariable int id, @RequestBody ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication) {
		return serviceKeyAdvantagesAplicationService.updateServiceKeyAdvantagesAplication(id, serviceKeyAdvantagesAplication);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		serviceKeyAdvantagesAplicationService.deleteServiceKeyAdvantagesAplication(id);
	}
}
