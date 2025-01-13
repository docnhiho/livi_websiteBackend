package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.Session;
import com.example.livi.model.TechAppliedTech;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechAppliedTechRepository;

@Service
public class TechAppliedTechService {

	@Autowired
	private TechAppliedTechRepository techAppliedTechRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<TechAppliedTech> geTechAppliedTech() {
		return techAppliedTechRepository.findAll();
	}

	public TechAppliedTech getTechAppliedTechById(int id) {
		return techAppliedTechRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public TechAppliedTech addTechAppliedTech(TechAppliedTech techAppliedTech, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		techAppliedTech.setSession(session);
		techAppliedTech.setThumbnail(base64Image);

		return techAppliedTechRepository.save(techAppliedTech);
	}

	public TechAppliedTech updateTechAppliedTech(int id, TechAppliedTech techAppliedTech, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<TechAppliedTech> optional = techAppliedTechRepository.findById(id);
		if (optional.isPresent()) {
			TechAppliedTech existingEntity = optional.get();
			if (techAppliedTech.getName() != null) {
				existingEntity.setName(techAppliedTech.getName());
			}
			if (techAppliedTech.getDescription() != null) {
				existingEntity.setDescription(techAppliedTech.getDescription());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return techAppliedTechRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}

	public void deleteTechAppliedTech(int id) {
		if (!techAppliedTechRepository.existsById(id)) {
			throw new RuntimeException("TechAppliedTech not found with id: " + id);
		}
		techAppliedTechRepository.deleteById(id);
	}
}
