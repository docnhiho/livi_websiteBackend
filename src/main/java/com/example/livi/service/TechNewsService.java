package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Session;
import com.example.livi.model.TechNews;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechNewsRepository;

@Service
public class TechNewsService {

	@Autowired
	private TechNewsRepository techNewsRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<TechNews> getTechNews(){
		return techNewsRepository.findAll();
	}
	
	public TechNews gettNewsById(int id) {
		return techNewsRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechNews addTechNews(TechNews techNews, int sessionId) {
		if (techNews != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			techNews.setSession(session);
			return techNewsRepository.save(techNews);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public TechNews updateTechNews(int id, TechNews techNews) {
		TechNews techNews2 = gettNewsById(id);
		if(techNews.getThumbnail() != null) {
			techNews2.setThumbnail(techNews.getThumbnail());
		}
		if(techNews.getName() != null) {
			techNews2.setName(techNews.getName());
		}
		if(techNews.getDescription() != null) {
			techNews2.setDescription(techNews.getDescription());
		}
		
		return techNewsRepository.save(techNews2);
	}
	
	public void deleteTechNews(int id) {
		if(!techNewsRepository.existsById(id)) {
	        throw new RuntimeException("TechNews not found with id: " + id);
		}
		techNewsRepository.deleteById(id);
	}
}
