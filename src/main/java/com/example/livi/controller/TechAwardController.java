package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.TechAward;
import com.example.livi.service.TechAwardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/techaward")
public class TechAwardController {

	@Autowired
	private TechAwardService techAwardService;
	
	@GetMapping("")
	public List<TechAward> getTechAwards(){
		return techAwardService.geTechAwards();
	}
	
	@GetMapping("/{id}")
	public TechAward gettAwardServiceById(@PathVariable int id) {
		return techAwardService.getTechAwardById(id);
	}
	
	@PostMapping("/{sessionId}")
	public TechAward addTechAward(@RequestBody TechAward techAward, @PathVariable int sessionId) {
		//TODO: process POST request
		return techAwardService.addTechAward(techAward, sessionId);
	}
	
	@PutMapping("/{id}")
	public TechAward putMethodName(@PathVariable int id, @RequestBody TechAward techAward) {
		//TODO: process PUT request
		return techAwardService.updateTechAward(id, techAward);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechAward(@PathVariable int id) {
		techAwardService.deleteTechAward(id);
	}
}
