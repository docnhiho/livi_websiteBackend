package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutAwardRecognition;
import com.example.livi.model.Session;
import com.example.livi.repository.AboutAwardRecognitionRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class AboutAwardRecognitionService {
	
	@Autowired
	private AboutAwardRecognitionRepository aboutAwardRecognitionRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<AboutAwardRecognition> getAboutAwardRecognitions(){
		return aboutAwardRecognitionRepository.findAll();
	}
	
	public AboutAwardRecognition getAboutAwardRecognitionById(int id) {
		return aboutAwardRecognitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public AboutAwardRecognition addAboutAwardRecognition(AboutAwardRecognition aboutAwardRecognition, int sessionId) {
		if (aboutAwardRecognition != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			aboutAwardRecognition.setSession(session);
			return aboutAwardRecognitionRepository.save(aboutAwardRecognition);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public AboutAwardRecognition updateAboutAwardRecognition(int id, AboutAwardRecognition aboutAwardRecognition) {
		AboutAwardRecognition aboutAwardRecognition2 = getAboutAwardRecognitionById(id);
		if(aboutAwardRecognition.getThumbnail() != null) {
			aboutAwardRecognition2.setThumbnail(aboutAwardRecognition.getThumbnail());
		}
		if(aboutAwardRecognition.getName() != null) {
			aboutAwardRecognition2.setName(aboutAwardRecognition.getName());
		}
		if(aboutAwardRecognition.getDescription() != null) {
			aboutAwardRecognition2.setDescription(aboutAwardRecognition.getDescription());
		}
		
		return aboutAwardRecognitionRepository.save(aboutAwardRecognition2);
	}
	
	public void deleteAboutAwardRecognition(int id) {
		if(!aboutAwardRecognitionRepository.existsById(id)) {
	        throw new RuntimeException("AboutAwardRecognition not found with id: " + id);
		}
		aboutAwardRecognitionRepository.deleteById(id);
	}

}
