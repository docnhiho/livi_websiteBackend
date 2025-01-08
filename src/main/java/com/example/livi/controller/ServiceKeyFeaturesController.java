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

import com.example.livi.model.ServiceKeyFeatures;
import com.example.livi.model.Session;
import com.example.livi.service.ServiceKeyFeaturesService;

@RestController
@RequestMapping("/servicekeyfeatures")
public class ServiceKeyFeaturesController {
	@Autowired
	private ServiceKeyFeaturesService serviceKeyFeatures;
	
	@GetMapping("")
	public List<ServiceKeyFeatures> getKeyFeatures() {
		return serviceKeyFeatures.getServiceKeyFeatures();
	}
	
	@GetMapping("{id}")
	public ServiceKeyFeatures getServiceKeyFeaturesById(@PathVariable int id) {
		return serviceKeyFeatures.getServiceKeyFeaturesById(id);
	}
	
	@PostMapping("{sessionId}")
	public ServiceKeyFeatures addServiceKeyFeatures(@RequestBody ServiceKeyFeatures serviceKeyFeature, @PathVariable int sessionId) {
		//TODO: process POST request
		return serviceKeyFeatures.addServiceKeyFeatures(serviceKeyFeature, sessionId);
	}
	
	@PutMapping("/{id}")
	public ServiceKeyFeatures updateServiceKeyFeatures(@PathVariable int id, @RequestBody ServiceKeyFeatures serviceKeyFeature) {
		return serviceKeyFeatures.updateServiceKeyFeatures(id, serviceKeyFeature);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		serviceKeyFeatures.deleteServiceKeyFeatures(id);
	}
}
