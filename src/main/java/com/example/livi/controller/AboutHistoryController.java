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

import com.example.livi.model.AboutHistory;
import com.example.livi.model.Session;
import com.example.livi.service.AboutHistoryService;

@RestController
@RequestMapping("/abouthistory")
public class AboutHistoryController {
	
	@Autowired
	private AboutHistoryService aboutHistoryService;

	@GetMapping("")
	public List<AboutHistory> getAboutHistories() {
		return aboutHistoryService.getAboutHistory();
	}
	
	@GetMapping("{id}")
	public AboutHistory getAboutHistoryById(@PathVariable int id) {
		return aboutHistoryService.getAboutHistoryById(id);
	}
	
	@PostMapping("{sessionId}")
	public AboutHistory addAboutHistory(@RequestBody AboutHistory aboutHistory, @PathVariable int sessionId) {
		//TODO: process POST request
		return aboutHistoryService.addAboutHistory(aboutHistory, sessionId);
	}
	
	@PutMapping("/{id}")
	public AboutHistory updateAboutHistory(@PathVariable int id, @RequestBody AboutHistory aboutHistory) {
		return aboutHistoryService.updateAboutHistory(id, aboutHistory);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAboutHistory(@PathVariable int id) {
		aboutHistoryService.deleteAboutHistory(id);
	}
	
}
