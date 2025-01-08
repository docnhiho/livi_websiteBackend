package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutRelatedNew;
import com.example.livi.model.Session;
import com.example.livi.repository.AboutRelatedNewRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class AboutRelatedNewService {
	@Autowired
	private AboutRelatedNewRepository aboutRelatedNewRepository;
	@Autowired
	private SessionRepository sessionRepository;



	public List<AboutRelatedNew> getAboutRelatedNews() {
		return aboutRelatedNewRepository.findAll();
	}

	public AboutRelatedNew getAboutRelatedNewById(int id) {
		return aboutRelatedNewRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public AboutRelatedNew addSBanner(AboutRelatedNew aboutRelatedNew, int sessionId) {
		if (aboutRelatedNew != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			aboutRelatedNew.setSession(session);
			return aboutRelatedNewRepository.save(aboutRelatedNew);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public AboutRelatedNew updateBanner(int id, AboutRelatedNew aboutRelatedNew) {
		AboutRelatedNew aboutRelatedNew2 = getAboutRelatedNewById(id);

		if (aboutRelatedNew2.getThumbnail() != null) {
			aboutRelatedNew2.setThumbnail(aboutRelatedNew.getThumbnail());
		}
		if (aboutRelatedNew2.getName() != null) {
			aboutRelatedNew2.setName(aboutRelatedNew.getName());
		}
		if (aboutRelatedNew2.getDescription() != null) {
			aboutRelatedNew2.setDescription(aboutRelatedNew.getDescription());
		}
		return aboutRelatedNewRepository.save(aboutRelatedNew2);
	}

	public void deleteBanner(int id) {
		if(!aboutRelatedNewRepository.existsById(id)) {
	        throw new RuntimeException("AboutRelatedNew not found with id: " + id);
		}
		aboutRelatedNewRepository.deleteById(id);
	}
}
