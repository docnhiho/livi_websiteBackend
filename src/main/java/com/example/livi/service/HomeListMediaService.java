package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.HomeListMedia;
import com.example.livi.model.Section;
import com.example.livi.repository.HomeListMediaRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class HomeListMediaService {
	@Autowired
	private HomeListMediaRepository homeListMediaRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public List<HomeListMedia> getHomeListMedias() {
		return homeListMediaRepository.findAll();
	}

	public HomeListMedia getHomeListMedia(int id) {
		return homeListMediaRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public HomeListMedia addHomeListMedia(HomeListMedia homeListMedia, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		homeListMedia.setSession(session);
		homeListMedia.setImage(base64Image);

		return homeListMediaRepository.save(homeListMedia);
	}

	public HomeListMedia updateHomeListMedia(int id, HomeListMedia homeListMedia, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<HomeListMedia> optional = homeListMediaRepository.findById(id);
		if (optional.isPresent()) {
			HomeListMedia existingEntity = optional.get();
			if (homeListMedia.getDescription() != null) {
				existingEntity.setDescription(homeListMedia.getDescription());
			}
			if (homeListMedia.getLang() != null) {
				existingEntity.setLang(homeListMedia.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getImage())) {
				existingEntity.setImage(base64Image);
			}
			return homeListMediaRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}

	public void deleteHomeListMedia(int id) {
		if(!homeListMediaRepository.existsById(id)) {
	        throw new RuntimeException("HomeListMedia not found with id: " + id);
		}
		homeListMediaRepository.deleteById(id);
	}
}
