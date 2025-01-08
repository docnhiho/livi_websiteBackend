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
import com.example.livi.model.HomelistClient;
import com.example.livi.service.HomelistClientService;

@RestController
@RequestMapping("/homelistclient")
public class HomelistClientController {

	@Autowired
	private HomelistClientService homelistClientService;
	
	@GetMapping("")
	public List<HomelistClient> getAllSessions() {
		return homelistClientService.getList();
	}
	
	@GetMapping("{id}")
	public HomelistClient getSessionById(@PathVariable int id) {
		return homelistClientService.getHomelistClientById(id);
	}
	
	@PostMapping("{sessionId}")
	public HomelistClient addSession(@RequestBody HomelistClient homelistClient, @PathVariable int sessionId) {
		//TODO: process POST request
		return homelistClientService.addHomelistClient(homelistClient, sessionId);
	}
	
	@PutMapping("/{id}")
	public HomelistClient updateSession(@PathVariable int id, @RequestBody HomelistClient homelistClient) {
		return homelistClientService.updateHomelistClient(id, homelistClient);
	}
	
	@DeleteMapping("/{id}")
	public void deleteHomelistClient(@PathVariable int id) {
		homelistClientService.deleteHomelistClient(id);
	}
}
