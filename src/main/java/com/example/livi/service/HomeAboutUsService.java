package com.example.livi.service;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.HomeAboutUs;
import com.example.livi.model.Session;
import com.example.livi.repository.HomeAboutUsRepository;
import com.example.livi.repository.SessionRepository;


@Service
public class HomeAboutUsService {
	@Autowired
	private HomeAboutUsRepository homeAboutUsRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<HomeAboutUs> getHomeAboutUs(){
		return homeAboutUsRepository.findAll();
	}
	
	public HomeAboutUs getHomeAboutUsById(int id) {
		return homeAboutUsRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public HomeAboutUs addHomeAboutUs(HomeAboutUs homeAboutUs, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		homeAboutUs.setSession(session);
		homeAboutUs.setImage(base64Image);

		return homeAboutUsRepository.save(homeAboutUs);
	}
	
    public HomeAboutUs updateHomeAboutUs(int id, HomeAboutUs homeAboutUs, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<HomeAboutUs> optional = homeAboutUsRepository.findById(id);
		if (optional.isPresent()) {
			HomeAboutUs existingEntity = optional.get();
			if (homeAboutUs.getHeadline() != null) {
				existingEntity.setHeadline(homeAboutUs.getHeadline());
			}
			if (homeAboutUs.getSubHeadline() != null) {
				existingEntity.setSubHeadline(homeAboutUs.getSubHeadline());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getImage())) {
				existingEntity.setImage(base64Image);
			}
			return homeAboutUsRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("homeAboutUs not found with ID " + id);
		}
    }
    
    public void deleteHomeAboutUs(int id) {
		if (!homeAboutUsRepository.existsById(id)) {
			throw new RuntimeException("AboutLiviLife not found with id: " + id);
		}
		homeAboutUsRepository.deleteById(id);
    }
}
