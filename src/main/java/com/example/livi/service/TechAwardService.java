package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Section;
import com.example.livi.model.TechAward;
import com.example.livi.repository.SectionRepository;
import com.example.livi.repository.TechAwardRepository;

@Service
public class TechAwardService {

	@Autowired
	private TechAwardRepository techAwardRepository;
	@Autowired
	
	private SectionRepository sessionRepository;
	
	public List<TechAward> geTechAwards(){
		return techAwardRepository.findAll();
	}
	
	public TechAward getTechAwardById(int id) {
		return techAwardRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechAward addTechAward(TechAward techAward, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		techAward.setSession(session);
		techAward.setThumbnail(base64Image);

		return techAwardRepository.save(techAward);
	}
	
	public TechAward updateTechAward(int id,TechAward techAward, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<TechAward> optional = techAwardRepository.findById(id);
		if (optional.isPresent()) {
			TechAward existingEntity = optional.get();
			if (techAward.getName() != null) {
				existingEntity.setName(techAward.getName());
			}
			if (techAward.getDescription() != null) {
				existingEntity.setDescription(techAward.getDescription());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return techAwardRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}
	
	public void deleteTechAward(int id) {
		if(!techAwardRepository.existsById(id)) {
	        throw new RuntimeException("TechAward not found with id: " + id);
		}
		techAwardRepository.deleteById(id);
	}
}
