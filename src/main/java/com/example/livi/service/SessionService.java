package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Session;
import com.example.livi.repository.SessionRepository;

@Service
public class SessionService implements SessionInterface{

	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public List<Session> getAllSessions() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll();
	}

}
