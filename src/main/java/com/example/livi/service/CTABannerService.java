package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public CTABanner addBanner(CTABanner ctaBanner, int sessionId) {
		if (ctaBanner != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			ctaBanner.setSession(session);
			return ctaBannerRepository.save(ctaBanner);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public CTABanner updateBanner(int id, CTABanner ctaBanner) {
		CTABanner ctaBanner2 = getBannerById(id);
		
		if(ctaBanner.getCoverImage() != null) {
			ctaBanner2.setCoverImage(ctaBanner.getCoverImage());
		}
		return ctaBannerRepository.save(ctaBanner2);
			
	}
	
	public void deleteBanner(int id) {
		if(!ctaBannerRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		sessionRepository.deleteById(id);
	}

}
