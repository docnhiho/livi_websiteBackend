package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Session;
import com.example.livi.model.TechDevelopmentProcess;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechDevelopmentProcessRepository;

@Service
public class TechDevelopmentProcessService {
	
	@Autowired
	private TechDevelopmentProcessRepository techDevelopmentProcessRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<TechDevelopmentProcess> geTechDevelopmentProcesses(){
		return techDevelopmentProcessRepository.findAll();
	}
	
	public TechDevelopmentProcess getTechDevelopmentProcessById(int id) {
		return techDevelopmentProcessRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechDevelopmentProcess addDevelopmentProcess(TechDevelopmentProcess techDevelopmentProcess, int sessionId) {
		if (techDevelopmentProcess != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			techDevelopmentProcess.setSession(session);
			return techDevelopmentProcessRepository.save(techDevelopmentProcess);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public TechDevelopmentProcess updaTechDevelopmentProcess(int id, TechDevelopmentProcess techDevelopmentProcess) {
		TechDevelopmentProcess techDevelopmentProcess2 = getTechDevelopmentProcessById(id);
		if(techDevelopmentProcess.getHeadline() != null) {
			techDevelopmentProcess2.setHeadline(techDevelopmentProcess.getHeadline());
		}
		if(techDevelopmentProcess.getSubHeadline() != null) {
			techDevelopmentProcess2.setSubHeadline(techDevelopmentProcess.getSubHeadline());
		}
		
		return techDevelopmentProcessRepository.save(techDevelopmentProcess2);
	}
	
	public void deleteTechDevelopmentProcess(int id) {
		if(!techDevelopmentProcessRepository.existsById(id)) {
	        throw new RuntimeException("TechDevelopmentProcessService not found with id: " + id);
		}
		techDevelopmentProcessRepository.deleteById(id);
	}
}
