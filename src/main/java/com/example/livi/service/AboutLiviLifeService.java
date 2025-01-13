package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutAwardRecognition;
import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.Session;
import com.example.livi.repository.AboutLiviLifeRepository;
import com.example.livi.repository.SessionRepository;


@Service
public class AboutLiviLifeService {
	@Autowired
	private AboutLiviLifeRepository aboutLiviLifeRepository;
	@Autowired
	private SessionRepository sessionRepository;
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public List<AboutLiviLife> getAboutLiviLifes(){
		return aboutLiviLifeRepository.findAll();
	}

	public AboutLiviLife getAboutLiviLifeById(int id) {
		return aboutLiviLifeRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	
	public AboutLiviLife addAboutLiviLife(AboutLiviLife aboutLiviLife, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		aboutLiviLife.setSession(session);
		aboutLiviLife.setImage(base64Image);

		return aboutLiviLifeRepository.save(aboutLiviLife);
	}
	
    public AboutLiviLife updateAboutLiviLife(int id, AboutLiviLife aboutLiviLife, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<AboutLiviLife> optional = aboutLiviLifeRepository.findById(id);
		if (optional.isPresent()) {
			AboutLiviLife existingEntity = optional.get();
			if (aboutLiviLife.getHeadLine() != null) {
				existingEntity.setHeadLine(aboutLiviLife.getHeadLine());
			}
			if (aboutLiviLife.getDescription() != null) {
				existingEntity.setDescription(aboutLiviLife.getDescription());
			}
			if (aboutLiviLife.getLink() != null) {
				existingEntity.setLink(aboutLiviLife.getLink());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getImage())) {
				existingEntity.setImage(base64Image);
			}
			return aboutLiviLifeRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
    }
	
	public void deleteAboutLiviLife(int id) {
		if (!aboutLiviLifeRepository.existsById(id)) {
			throw new RuntimeException("AboutLiviLife not found with id: " + id);
		}
		aboutLiviLifeRepository.deleteById(id);
	}
}
