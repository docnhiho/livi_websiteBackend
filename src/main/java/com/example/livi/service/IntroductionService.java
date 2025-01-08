package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Introduction;
import com.example.livi.model.Session;
import com.example.livi.repository.IntroductionRepository;
import com.example.livi.repository.SessionRepository;


@Service
public class IntroductionService {
	
	@Autowired
	private IntroductionRepository introductionRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<Introduction> getAllIntroductions(){
		return introductionRepository.findAll();
	}
	
	public Introduction getIntroductionById(int id) {
		return introductionRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public Introduction addIntroduction(Introduction introduction, int sessionId) {
		if (introduction != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			introduction.setSession(session);
			return introductionRepository.save(introduction);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public Introduction updaIntroduction(int id, Introduction introduction) {
		Introduction introduction2 = getIntroductionById(id);
		
		if(introduction.getTitle() != null) {
			introduction2.setTitle(introduction.getTitle());
		}
		if(introduction.getDescription() != null) {
			introduction2.setDescription(introduction.getDescription());
		}
		return introductionRepository.save(introduction2);
		
	}
	
	public void deleteIntroduction(int id) {
		if(!introductionRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		introductionRepository.deleteById(id);
	}

}