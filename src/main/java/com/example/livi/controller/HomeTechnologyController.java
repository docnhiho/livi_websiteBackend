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

import com.example.livi.model.HomeTechnology;
import com.example.livi.model.Section;
import com.example.livi.service.HomeTechnologyService;

@RestController
@RequestMapping("/hometechnology")
public class HomeTechnologyController {

	@Autowired
	private HomeTechnologyService homeTechnologyService;
	
	@GetMapping("")
	public List<HomeTechnology> geTechnologies() {
		return homeTechnologyService.geTechnologies();
	}
	
	@GetMapping("{id}")
	public HomeTechnology geHomeTechnology(@PathVariable int id) {
		return homeTechnologyService.geTechnologyById(id);
	}
	
	@PostMapping("{sessionId}")
	public HomeTechnology addTechnology(@RequestBody HomeTechnology homeTechnology, @PathVariable int sessionId) {
		//TODO: process POST request
		return homeTechnologyService.addHomeTechnology(homeTechnology, sessionId);
	}
	
	@PutMapping("/{id}")
	public HomeTechnology updateTHomeTechnology(@PathVariable int id, @RequestBody HomeTechnology homeTechnology) {
		return homeTechnologyService.updateHomeTechnology(id, homeTechnology);
	}
	
	@DeleteMapping("/{id}")
	public void deleteHomeTechnology(@PathVariable int id) {
		homeTechnologyService.deleteHomeTechnology(id);
	}
}
