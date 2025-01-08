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

import com.example.livi.model.HomeListMedia;
import com.example.livi.service.HomeListMediaService;

@RestController
@RequestMapping("/homelistmedia")
public class HomeListMediaController {

	@Autowired
	private HomeListMediaService homeListMediaService;
	
	@GetMapping("")
	public List<HomeListMedia> getAllSessions() {
		return homeListMediaService.getHomeListMedias();
	}
	
	@GetMapping("{id}")
	public HomeListMedia getSessionById(@PathVariable int id) {
		return homeListMediaService.getHomeListMedia(id);
	}
	
	@PostMapping("{sessionId}")
	public HomeListMedia addSession(@RequestBody HomeListMedia homeListMedia, @PathVariable int sessionId) {
		//TODO: process POST request
		return homeListMediaService.addHomeListMedia(homeListMedia, sessionId);
	}
	
	@PutMapping("/{id}")
	public HomeListMedia updateSession(@PathVariable int id, @RequestBody HomeListMedia homeListMedia) {
		return homeListMediaService.updateHomeListMedia(id, homeListMedia);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		homeListMediaService.deleteHomeListMedia(id);
	}
}
