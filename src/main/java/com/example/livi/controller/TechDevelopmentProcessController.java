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

import com.example.livi.model.TechDevelopmentProcess;
import com.example.livi.service.TechDevelopmentProcessService;

@RestController
@RequestMapping("/techdevelopmentprocess")
public class TechDevelopmentProcessController {

	@Autowired
	private TechDevelopmentProcessService techDevelopmentProcessService;
	
	@GetMapping("")
	public List<TechDevelopmentProcess> geTechDevelopmentProcesses(){
		return techDevelopmentProcessService.geTechDevelopmentProcesses();
	}
	
	@GetMapping("/{id}")
	public TechDevelopmentProcess geTechDevelopmentProcessById(@PathVariable int id) {
		return techDevelopmentProcessService.getTechDevelopmentProcessById(id);
	}
	
	@PostMapping("/{sessionId}")
	public TechDevelopmentProcess addDevelopmentProcess(@RequestBody TechDevelopmentProcess techDevelopmentProcess, @PathVariable int sessionId){
		//TODO: process POST request
		return techDevelopmentProcessService.addDevelopmentProcess(techDevelopmentProcess, sessionId);
	}
	
	@PutMapping("/{id}")
	public TechDevelopmentProcess  updateDevelopmentProcess(@PathVariable int id, @RequestBody TechDevelopmentProcess techDevelopmentProcess) {
		//TODO: process PUT request
		return techDevelopmentProcessService.updaTechDevelopmentProcess(id, techDevelopmentProcess);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechDevelopmentProcess(@PathVariable int id) {
		techDevelopmentProcessService.deleteTechDevelopmentProcess(id);
	}
}
