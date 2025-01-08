package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Session;
import com.example.livi.model.TechAward;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechAwardRepository;

@Service
public class TechAwardService {

	@Autowired
	private TechAwardRepository techAwardRepository;
	@Autowired
	
	private SessionRepository sessionRepository;
	
	public List<TechAward> geTechAwards(){
		return techAwardRepository.findAll();
	}
	
	public TechAward getTechAwardById(int id) {
		return techAwardRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechAward addTechAward(TechAward techAward, int sessionId) {
		if (techAward != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			techAward.setSession(session);
			return techAwardRepository.save(techAward);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public TechAward updateTechAward(int id,TechAward techAward) {
		TechAward techAward2 = getTechAwardById(id);
		
		if(techAward.getThumbnail() != null) {
			techAward2.setThumbnail(techAward.getThumbnail());
		}
		if(techAward.getName() != null) {
			techAward2.setName(techAward.getName());
		}
		if(techAward.getDescription() != null) {
			techAward2.setDescription(techAward.getDescription());
		}
		return techAwardRepository.save(techAward2);
	}
	
	public void deleteTechAward(int id) {
		if(!techAwardRepository.existsById(id)) {
	        throw new RuntimeException("TechAward not found with id: " + id);
		}
		techAwardRepository.deleteById(id);
	}
}
