package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.livi.model.HomeListMedia;
import com.example.livi.model.Session;
import com.example.livi.repository.HomeListMediaRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class HomeListMediaService {
	@Autowired
	private HomeListMediaRepository homeListMediaRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<HomeListMedia> getHomeListMedias() {
		return homeListMediaRepository.findAll();
	}

	public HomeListMedia getHomeListMedia(int id) {
		return homeListMediaRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public HomeListMedia addHomeListMedia(HomeListMedia homeListMedia, int sessionId) {
		if (homeListMedia != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			homeListMedia.setSession(session);
			return homeListMediaRepository.save(homeListMedia);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public HomeListMedia updateHomeListMedia(int id, HomeListMedia homeListMedia) {
		HomeListMedia homeListMedia2 = getHomeListMedia(id);
		if (homeListMedia.getImage() != null) {
			homeListMedia2.setImage(homeListMedia.getImage());
		}
		if (homeListMedia.getDescription() != null) {
			homeListMedia2.setDescription(homeListMedia.getDescription());
		}
		return homeListMediaRepository.save(homeListMedia2);
	}

	public void deleteHomeListMedia(int id) {
		if(!homeListMediaRepository.existsById(id)) {
	        throw new RuntimeException("HomeListMedia not found with id: " + id);
		}
		homeListMediaRepository.deleteById(id);
	}
}
