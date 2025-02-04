package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.Section;
import com.example.livi.model.TechNews;
import com.example.livi.repository.SectionRepository;
import com.example.livi.repository.TechNewsRepository;

@Service
public class TechNewsService {

	@Autowired
	private TechNewsRepository techNewsRepository;
	@Autowired
	private SectionRepository sessionRepository;
	
	public List<TechNews> getTechNews(){
		return techNewsRepository.findAll();
	}
	
	public TechNews gettNewsById(int id) {
		return techNewsRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechNews addTechNews(TechNews techNews, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		techNews.setSession(session);
		techNews.setThumbnail(base64Image);

		return techNewsRepository.save(techNews);
	}
	
	public TechNews updateTechNews(int id, TechNews techNews, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<TechNews> optional = techNewsRepository.findById(id);
		if (optional.isPresent()) {
			TechNews existingEntity = optional.get();
			if (techNews.getName() != null) {
				existingEntity.setName(techNews.getName());
			}
			if (techNews.getDescription() != null) {
				existingEntity.setDescription(techNews.getDescription());
			}
			if (techNews.getLang() != null) {
				existingEntity.setLang(techNews.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return techNewsRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}
	
	public void deleteTechNews(int id) {
		if(!techNewsRepository.existsById(id)) {
	        throw new RuntimeException("TechNews not found with id: " + id);
		}
		techNewsRepository.deleteById(id);
	}
}
