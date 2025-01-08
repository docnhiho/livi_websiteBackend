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

import com.example.livi.model.ServiceMoreInfo;
import com.example.livi.service.ServiceMoreInfoSerivce;

@RestController
@RequestMapping("/servicemoreinfo")
public class ServiceMoreInfoController {

	@Autowired
	private ServiceMoreInfoSerivce serviceMoreInfoSerivce;
	
	@GetMapping("")
	public List<ServiceMoreInfo> geServiceMoreInfos() {
		return serviceMoreInfoSerivce.getServiceMoreInfo();
	}
	
	@GetMapping("{id}")
	public ServiceMoreInfo getServiceMoreInfoById(@PathVariable int id) {
		return serviceMoreInfoSerivce.getServiceMoreInfoById(id);
	}
	
	@PostMapping("{sessionId}")
	public ServiceMoreInfo addServiceMoreInfo(@RequestBody ServiceMoreInfo serviceMoreInfo, @PathVariable int sessionId) {
		//TODO: process POST request
		return serviceMoreInfoSerivce.addServiceMoreInfo(serviceMoreInfo, sessionId);
	}
	
	@PutMapping("/{id}")
	public ServiceMoreInfo updaServiceMoreInfo(@PathVariable int id, @RequestBody ServiceMoreInfo serviceMoreInfo) {
		return serviceMoreInfoSerivce.updateServiceMoreInfo(id, serviceMoreInfo);
	}
	
	@DeleteMapping("/{id}")
	public void deleteServiceMoreInfo(@PathVariable int id) {
		serviceMoreInfoSerivce.deleteServiceMoreInfo(id);
	}
}
