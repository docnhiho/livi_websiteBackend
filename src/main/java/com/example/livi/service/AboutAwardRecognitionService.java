package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutAwardRecognition;
import com.example.livi.model.Section;
import com.example.livi.repository.AboutAwardRecognitionRepository;
import com.example.livi.repository.SectionRepository;

import jakarta.transaction.Transactional;

@Service
public class AboutAwardRecognitionService {
	@Autowired
	private AboutAwardRecognitionRepository aboutAwardRecognitionRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public AboutAwardRecognitionService(AboutAwardRecognitionRepository aboutAwardRecognitionRepository,
			SectionRepository sessionRepository) {
		super();
		this.aboutAwardRecognitionRepository = aboutAwardRecognitionRepository;
		this.sessionRepository = sessionRepository;
	}

	public List<AboutAwardRecognition> getAboutAwardRecognitions() {
		return aboutAwardRecognitionRepository.findAll();
	}

	public AboutAwardRecognition getAboutAwardRecognitionById(int id) {
		return aboutAwardRecognitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}



	public AboutAwardRecognition addAboutAwardRecognition(AboutAwardRecognition aboutAwardRecognition, int sessionId,
			byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		aboutAwardRecognition.setSession(session);
		aboutAwardRecognition.setThumbnail(base64Image);

		return aboutAwardRecognitionRepository.save(aboutAwardRecognition);
	}

	public AboutAwardRecognition updateAboutAwardRecognition(int id, AboutAwardRecognition aboutAwardRecognition,
			byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<AboutAwardRecognition> optionalAboutAwardRecognition = aboutAwardRecognitionRepository.findById(id);
		if (optionalAboutAwardRecognition.isPresent()) {
			AboutAwardRecognition existingEntity = optionalAboutAwardRecognition.get();
			if (aboutAwardRecognition.getName() != null
					&& !aboutAwardRecognition.getName().equals(existingEntity.getName())) {
				existingEntity.setName(aboutAwardRecognition.getName());
			}
			if (aboutAwardRecognition.getDescription() != null
					&& !aboutAwardRecognition.getDescription().equals(existingEntity.getDescription())) {
				existingEntity.setDescription(aboutAwardRecognition.getDescription());
			}
			if (aboutAwardRecognition.getLang() != null
					&& !aboutAwardRecognition.getLang().equals(existingEntity.getLang())) {
				existingEntity.setLang(aboutAwardRecognition.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return aboutAwardRecognitionRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}

	public void deleteAboutAwardRecognition(int id) {
		if (!aboutAwardRecognitionRepository.existsById(id)) {
			throw new RuntimeException("AboutAwardRecognition not found with id: " + id);
		}
		aboutAwardRecognitionRepository.deleteById(id);
	}

}
