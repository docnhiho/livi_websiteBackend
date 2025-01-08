package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Session;
import com.example.livi.model.TechAppliedTech;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechAppliedTechRepository;

@Service
public class TechAppliedTechService {

	@Autowired
	private TechAppliedTechRepository techAppliedTechRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<TechAppliedTech> geTechAppliedTech(){
		return techAppliedTechRepository.findAll();
	}
	
	public TechAppliedTech getTechAppliedTechById(int id) {
		return techAppliedTechRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechAppliedTech addTechAppliedTech(TechAppliedTech techAppliedTech, int sessionId) {
		if (techAppliedTech != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			techAppliedTech.setSession(session);
			return techAppliedTechRepository.save(techAppliedTech);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public TechAppliedTech updateTechAppliedTech(int id, TechAppliedTech techAppliedTech) {
		TechAppliedTech techAppliedTech2 = getTechAppliedTechById(id);
		if(techAppliedTech.getThumbnail() !=null) {
			techAppliedTech2.setThumbnail(techAppliedTech.getThumbnail());
		}
		if(techAppliedTech.getName() !=null) {
			techAppliedTech2.setName(techAppliedTech.getName());
		}
		if(techAppliedTech.getDescription() !=null) {
			techAppliedTech2.setDescription(techAppliedTech.getDescription());
		}
		
		return techAppliedTechRepository.save(techAppliedTech2);
	}
	
	public void deleteTechAppliedTech(int id) {
		if(!techAppliedTechRepository.existsById(id)) {
	        throw new RuntimeException("TechAppliedTech not found with id: " + id);
		}
		techAppliedTechRepository.deleteById(id);
	}
}
