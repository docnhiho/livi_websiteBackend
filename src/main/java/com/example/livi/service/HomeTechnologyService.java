package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.HomeTechnology;
import com.example.livi.model.Session;
import com.example.livi.repository.HomeTechnologyRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class HomeTechnologyService {

	@Autowired
	private HomeTechnologyRepository homeTechnologyRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<HomeTechnology> geTechnologies(){
		return homeTechnologyRepository.findAll();
	}
	
	public HomeTechnology geTechnologyById(int id) {
		return homeTechnologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public HomeTechnology addHomeTechnology(HomeTechnology homeTechnology, int sessionId) {
		if (homeTechnology != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			homeTechnology.setSession(session);
			return homeTechnologyRepository.save(homeTechnology);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public HomeTechnology updateHomeTechnology(int id, HomeTechnology homeTechnology) {
		HomeTechnology homeTechnology2 = geTechnologyById(id);
		if(homeTechnology.getTechAward() != null) {
			homeTechnology2.setTechAward(homeTechnology.getTechAward());
		}
		if(homeTechnology.getTechListing() != null) {
			homeTechnology2.setTechListing(homeTechnology.getTechListing());
		}
		if(homeTechnology.getTechNews() != null) {
			homeTechnology2.setTechNews(homeTechnology.getTechNews());
		}
		return homeTechnologyRepository.save(homeTechnology2);
	}
	
	public void deleteHomeTechnology(int id) {
		if(!homeTechnologyRepository.existsById(id)) {
	        throw new RuntimeException("HomeTechnology not found with id: " + id);
		}
		homeTechnologyRepository.deleteById(id);
	}
}
