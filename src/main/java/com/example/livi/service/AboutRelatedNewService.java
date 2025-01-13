package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
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

	public AboutRelatedNew addAboutRelatedNew(AboutRelatedNew aboutRelatedNew, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		aboutRelatedNew.setSession(session);
		aboutRelatedNew.setThumbnail(base64Image);

		return aboutRelatedNewRepository.save(aboutRelatedNew);
	}

	public AboutRelatedNew update(int id, AboutRelatedNew aboutRelatedNew, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<AboutRelatedNew> optional = aboutRelatedNewRepository.findById(id);
		if (optional.isPresent()) {
			AboutRelatedNew existingEntity = optional.get();
			if (aboutRelatedNew.getName() != null) {
				existingEntity.setName(aboutRelatedNew.getName());
			}
			if (aboutRelatedNew.getDescription() != null) {
				existingEntity.setDescription(aboutRelatedNew.getDescription());
			}

			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return aboutRelatedNewRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}

	public void delete(int id) {
		if(!aboutRelatedNewRepository.existsById(id)) {
	        throw new RuntimeException("AboutRelatedNew not found with id: " + id);
		}
		aboutRelatedNewRepository.deleteById(id);
	}
}
