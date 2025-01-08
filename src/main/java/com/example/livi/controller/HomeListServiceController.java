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

import com.example.livi.model.HomeListService;
import com.example.livi.service.HomeListServiceService;

@RestController
@RequestMapping("/homelistservice")
public class HomeListServiceController {

	@Autowired
	private HomeListServiceService homeListServiceService;
	
	@GetMapping("")
	public List<HomeListService> getAllSessions() {
		return homeListServiceService.getHomeListServices();
	}
	
	@GetMapping("{id}")
	public HomeListService getSessionById(@PathVariable int id) {
		return homeListServiceService.getHomeListServiceById(id);
	}
	
	@PostMapping("{sessionId}")
	public HomeListService addSession(@RequestBody HomeListService homeListService, @PathVariable int sessionId) {
		//TODO: process POST request
		return homeListServiceService.addHomeListService(homeListService, sessionId);
	}
	
	@PutMapping("/{id}")
	public HomeListService updateSession(@PathVariable int id, @RequestBody HomeListService homeListService) {
		return homeListServiceService.updateHomeListService(id, homeListService);
	}
	
	@DeleteMapping("/{id}")
	public void deleteHomeListService(@PathVariable int id) {
		homeListServiceService.deleteHomeListService(id);
	}
}
