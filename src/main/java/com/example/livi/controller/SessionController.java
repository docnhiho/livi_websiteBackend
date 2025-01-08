package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.Session;
import com.example.livi.service.SessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	

	@GetMapping("")
	public List<Session> getAllSessions() {
		return sessionService.getAllSessions();
	}
	
	@GetMapping("{id}")
	public Session getSessionById(@PathVariable int id) {
		return sessionService.getSessionById(id);
	}
	
	@PostMapping("{pageId}")
	public Session addSession(@RequestBody Session session, @PathVariable int pageId) {
		//TODO: process POST request
		return sessionService.addSession(session, pageId);
	}
	
	@PutMapping("/{id}")
	public Session updateSession(@PathVariable int id, @RequestBody Session session) {
		return sessionService.updateSession(id, session);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		sessionService.deleteSession(id);
	}
}
