package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.TechAppliedTech;
import com.example.livi.service.TechAppliedTechService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/techappliedtech")
public class TechAppliedTechController {
	
	@Autowired
	private TechAppliedTechService techAppliedTechService;
	
	@GetMapping("")
	public List<TechAppliedTech> geTechAppliedTech() {
		return techAppliedTechService.geTechAppliedTech();
	}
	
	@GetMapping("/{id}")
	public TechAppliedTech getTechAppliedTechById(@PathVariable int id) {
		return techAppliedTechService.getTechAppliedTechById(id);
	}
	
	@PostMapping("/{sessionId}")
	public TechAppliedTech addTechAppliedTech(@RequestBody TechAppliedTech techAppliedTech, @PathVariable int id ) {
		//TODO: process POST request
		return techAppliedTechService.addTechAppliedTech(techAppliedTech, id);
	}
	
	@PutMapping("/{id}")
	public TechAppliedTech updateTechAppliedTech(@PathVariable int id, @RequestBody TechAppliedTech techAppliedTech) {
		//TODO: process PUT request
		return techAppliedTechService.updateTechAppliedTech(id, techAppliedTech);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechAppliedTech(@PathVariable int id) {
		techAppliedTechService.deleteTechAppliedTech(id);
	}

}
