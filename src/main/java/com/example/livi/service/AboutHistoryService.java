package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutHistory;
import com.example.livi.model.Section;
import com.example.livi.repository.AboutHistoryRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class AboutHistoryService {
	@Autowired
	private AboutHistoryRepository aboutHistoryRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public List<AboutHistory> getAboutHistory() {
		return aboutHistoryRepository.findAll();
	}

	public AboutHistory getAboutHistoryById(int id) {
		return aboutHistoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public AboutHistory addAboutHistory(AboutHistory aboutHistory, int sessionId) {
		if (aboutHistory != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			aboutHistory.setSession(session);
			return aboutHistoryRepository.save(aboutHistory);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public AboutHistory updateAboutHistory(int id, AboutHistory aboutHistory) {
		AboutHistory aboutHistory2 = getAboutHistoryById(id);
		if (aboutHistory.getYear() != null) {
			aboutHistory2.setYear(aboutHistory.getYear());
		}
		if (aboutHistory.getMonth() != null) {
			aboutHistory2.setMonth(aboutHistory.getMonth());
		}
		if (aboutHistory.getDescription() != null) {
			aboutHistory2.setDescription(aboutHistory.getDescription());
		}
		if (aboutHistory.getLang() != null) {
			aboutHistory2.setLang(aboutHistory.getLang());
		}
		return aboutHistoryRepository.save(aboutHistory2);
	}
	
	public void deleteAboutHistory(int id) {
		if(!aboutHistoryRepository.existsById(id)) {
	        throw new RuntimeException("AboutHistory not found with id: " + id);
		}
		aboutHistoryRepository.deleteById(id);
	}
}
