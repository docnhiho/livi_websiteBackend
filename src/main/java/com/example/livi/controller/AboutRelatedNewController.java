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

import com.example.livi.model.AboutRelatedNew;
import com.example.livi.model.Session;
import com.example.livi.service.AboutRelatedNewService;

@RestController
@RequestMapping("/aboutrelatednew")
public class AboutRelatedNewController {

	@Autowired
	private AboutRelatedNewService aboutRelatedNewService;
	
	@GetMapping("")
	public List<AboutRelatedNew> getAllSessions() {
		return aboutRelatedNewService.getAboutRelatedNews();
	}
	
	@GetMapping("{id}")
	public AboutRelatedNew getSessionById(@PathVariable int id) {
		return aboutRelatedNewService.getAboutRelatedNewById(id);
	}
	
	@PostMapping("{sessionId}")
	public AboutRelatedNew addSession(@RequestBody AboutRelatedNew aboutRelatedNew, @PathVariable int sessionId) {
		//TODO: process POST request
		return aboutRelatedNewService.addSBanner(aboutRelatedNew, sessionId);
	}
	
	@PutMapping("/{id}")
	public AboutRelatedNew updateSession(@PathVariable int id, @RequestBody AboutRelatedNew aboutRelatedNew) {
		return aboutRelatedNewService.updateBanner(id, aboutRelatedNew);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAboutRelatedNew(@PathVariable int id) {
		aboutRelatedNewService.deleteBanner(id);
	}
}
