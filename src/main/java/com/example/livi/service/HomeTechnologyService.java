package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.HomeTechnology;
import com.example.livi.model.Section;
import com.example.livi.repository.HomeTechnologyRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class HomeTechnologyService {

	@Autowired
	private HomeTechnologyRepository homeTechnologyRepository;
	@Autowired
	private SectionRepository sessionRepository;
	
	public List<HomeTechnology> geTechnologies(){
		return homeTechnologyRepository.findAll();
	}
	
	public HomeTechnology geTechnologyById(int id) {
		return homeTechnologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public HomeTechnology addHomeTechnology(HomeTechnology homeTechnology, int sessionId) {
		if (homeTechnology != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			homeTechnology.setSession(session);
			return homeTechnologyRepository.save(homeTechnology);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public HomeTechnology updateHomeTechnology(int id, HomeTechnology homeTechnology) {
		HomeTechnology homeTechnology2 = geTechnologyById(id);
		if(homeTechnology.getAboutAwardRecognition() != null) {
			homeTechnology2.setAboutAwardRecognition(homeTechnology.getAboutAwardRecognition());
		}
		if(homeTechnology.getTechListing() != null) {
			homeTechnology2.setTechListing(homeTechnology.getTechListing());
		}
		if(homeTechnology.getMediaList() != null) {
			homeTechnology2.setMediaList(homeTechnology.getMediaList());
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
