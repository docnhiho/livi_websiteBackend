package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.TechKeyCapability;
import com.example.livi.service.TechKeyCapabilityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/techkeycapability")
public class TechKeyCapabilityController {
	
	@Autowired
	private TechKeyCapabilityService techKeyCapabilityService;
	
	@GetMapping("")
	public List<TechKeyCapability> getTechKeyCapabilities(){
		return techKeyCapabilityService.getTechKeyCapabilities();
	}
	
	@GetMapping("/{id}")
	public TechKeyCapability getTechKeyCapabilityById(@PathVariable int id) {
		return techKeyCapabilityService.getTechKeyCapabilityById(id);
	}
	
	@PostMapping("/{sessionId}")
	public TechKeyCapability addTechKeyCapability(@RequestBody TechKeyCapability techKeyCapability, @PathVariable int sessionId) {
		return techKeyCapabilityService.addTechKeyCapability(techKeyCapability, sessionId);
	}
	
	@PutMapping("path/{id}")
	public TechKeyCapability updatetTechKeyCapability(@PathVariable int id, @RequestBody TechKeyCapability techKeyCapability) {
		//TODO: process PUT request
		return techKeyCapabilityService.updateTechKeyCapability(id, techKeyCapability);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechKeyCapability(@PathVariable int id) {
		techKeyCapabilityService.deleteTechKeyCapability(id);
	}
}
