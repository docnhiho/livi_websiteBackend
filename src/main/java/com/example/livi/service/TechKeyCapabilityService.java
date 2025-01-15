package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.Session;
import com.example.livi.model.TechKeyCapability;
import com.example.livi.repository.SessionRepository;
import com.example.livi.repository.TechKeyCapabilityRepository;

@Service
public class TechKeyCapabilityService {
	
	@Autowired
	private TechKeyCapabilityRepository techKeyCapabilityRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<TechKeyCapability> getTechKeyCapabilities(){
		return techKeyCapabilityRepository.findAll();
	}
	
	public TechKeyCapability getTechKeyCapabilityById(int id) {
		return techKeyCapabilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechKeyCapability addTechKeyCapability(TechKeyCapability techKeyCapability, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		techKeyCapability.setSession(session);
		techKeyCapability.setThumbnail(base64Image);

		return techKeyCapabilityRepository.save(techKeyCapability);
	}
	
	public TechKeyCapability updateTechKeyCapability(int id, TechKeyCapability techKeyCapability, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<TechKeyCapability> optional = techKeyCapabilityRepository.findById(id);
		if (optional.isPresent()) {
			TechKeyCapability existingEntity = optional.get();
			if (techKeyCapability.getHeadline() != null) {
				existingEntity.setHeadline(techKeyCapability.getHeadline());
			}
			if (techKeyCapability.getDescription() != null) {
				existingEntity.setDescription(techKeyCapability.getDescription());
			}
			if (techKeyCapability.getButtonText() != null) {
				existingEntity.setButtonText(techKeyCapability.getButtonText());
			}
			if (techKeyCapability.getLink() != null) {
				existingEntity.setLink(techKeyCapability.getLink());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return techKeyCapabilityRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}
	
	public void deleteTechKeyCapability(int id) {
		if(!techKeyCapabilityRepository.existsById(id)) {
	        throw new RuntimeException("TechKeyCapability not found with id: " + id);
		}
		techKeyCapabilityRepository.deleteAllById(null);
	}
}
