package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.CTABanner;
import com.example.livi.model.Session;
import com.example.livi.repository.CTABannerRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class CTABannerService {

	@Autowired
	private CTABannerRepository ctaBannerRepository;

	@Autowired
	private SessionRepository sessionRepository;

	public List<CTABanner> getAllBanner() {
		return ctaBannerRepository.findAll();
	}

	public CTABanner getBannerById(int id) {
		return ctaBannerRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public CTABanner addBanner(CTABanner ctaBanner, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		ctaBanner.setSession(session);
		ctaBanner.setCoverImage(base64Image);

		return ctaBannerRepository.save(ctaBanner);
	}
	
	public CTABanner updateBanner(int id, CTABanner ctaBanner, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<CTABanner> optional = ctaBannerRepository.findById(id);
		if (optional.isPresent()) {
			CTABanner existingEntity = optional.get();

			if (base64Image != null && !base64Image.equals(existingEntity.getCoverImage())) {
				existingEntity.setCoverImage(base64Image);
			}
			return ctaBannerRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}
	
	public void deleteBanner(int id) {
		if(!ctaBannerRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		sessionRepository.deleteById(id);
	}

}
