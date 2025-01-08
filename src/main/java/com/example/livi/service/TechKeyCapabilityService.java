package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Session;
import com.example.livi.model.TechKeyCapability;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechKeyCapabilityRepository;

@Service
public class TechKeyCapabilityService {
	
	@Autowired
	private TechKeyCapabilityRepository techKeyCapabilityRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<TechKeyCapability> getTechKeyCapabilities(){
		return techKeyCapabilityRepository.findAll();
	}
	
	public TechKeyCapability getTechKeyCapabilityById(int id) {
		return techKeyCapabilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechKeyCapability addTechKeyCapability(TechKeyCapability techKeyCapability, int sessionId) {
		if (techKeyCapability != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			techKeyCapability.setSession(session);
			return techKeyCapabilityRepository.save(techKeyCapability);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public TechKeyCapability updateTechKeyCapability(int id, TechKeyCapability techKeyCapability) {
		TechKeyCapability techKeyCapability2 = getTechKeyCapabilityById(id);
		if(techKeyCapability.getHeadline() != null) {
			techKeyCapability2.setHeadline(techKeyCapability.getHeadline());
		}
		if(techKeyCapability.getSubHeadline() != null) {
			techKeyCapability2.setSubHeadline(techKeyCapability.getSubHeadline());
		}
		return techKeyCapabilityRepository.save(techKeyCapability2);
	}
	
	public void deleteTechKeyCapability(int id) {
		if(!techKeyCapabilityRepository.existsById(id)) {
	        throw new RuntimeException("TechKeyCapability not found with id: " + id);
		}
		techKeyCapabilityRepository.deleteAllById(null);
	}
}
