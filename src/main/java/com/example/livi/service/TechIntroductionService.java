package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.livi.model.Section;
import com.example.livi.model.TechIntroduction;
import com.example.livi.repository.SectionRepository;
import com.example.livi.repository.TechIntroductionRepository;

@Service
public class TechIntroductionService {

	@Autowired
	private TechIntroductionRepository techIntroductionRepository;
	@Autowired
	private SectionRepository sessionRepository;
	
	public List<TechIntroduction> getTechIntroductions(){
		return techIntroductionRepository.findAll();
	}
	
	public TechIntroduction gettTechIntroductionById(int id) {
		return techIntroductionRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));

	}
	
	public TechIntroduction addTechIntroduction(TechIntroduction techIntroduction, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		techIntroduction.setSession(session);
		techIntroduction.setIcon(base64Image);

		return techIntroductionRepository.save(techIntroduction);
	}
	
    public TechIntroduction updateTechIntroduction(int id, TechIntroduction techIntroduction, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<TechIntroduction> optional = techIntroductionRepository.findById(id);
		if (optional.isPresent()) {
			TechIntroduction existingEntity = optional.get();
			if (techIntroduction.getHeadline() != null) {
				existingEntity.setHeadline(techIntroduction.getHeadline());
			}
			if (techIntroduction.getDescription() != null) {
				existingEntity.setDescription(techIntroduction.getDescription());
			}
			if (techIntroduction.getLang() != null) {
				existingEntity.setLang(techIntroduction.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getIcon())) {
				existingEntity.setIcon(base64Image);
			}
			return techIntroductionRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("TechIntroduction not found with ID " + id);
		}
    }
    
	public void deleteTechIntroduction(int id) {
		if (!techIntroductionRepository.existsById(id)) {
			throw new RuntimeException("AboutLiviLife not found with id: " + id);
		}
		techIntroductionRepository.deleteById(id);
	}
}
