package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.Session;
import com.example.livi.repository.SessionRepository;
import com.example.livi.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	
	private SessionRepository sessionRepository;
	
	@GetMapping("")
	public List<Session> getAllSessions() {
		return sessionService.getAllSessions();
	}
	
}
