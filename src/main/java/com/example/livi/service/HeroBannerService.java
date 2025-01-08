package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.HeroBanner;
import com.example.livi.model.Page;
import com.example.livi.model.Session;
import com.example.livi.repository.HeroBannerRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class HeroBannerService {

	@Autowired
	private HeroBannerRepository heroBannerRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public HeroBannerService(HeroBannerRepository heroBannerRepository) {
		super();
		this.heroBannerRepository = heroBannerRepository;
	}

	public List<HeroBanner> getAllBanner() {
		return heroBannerRepository.findAll();
	}

	public HeroBanner getBannerById(int id) {
		return heroBannerRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public HeroBanner addSBanner(HeroBanner heroBanner, int sessionId) {
		if (heroBanner != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			heroBanner.setSession(session);
			return heroBannerRepository.save(heroBanner);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public HeroBanner updateBanner(int id, HeroBanner banner) {
		HeroBanner existingBanner = getBannerById(id);

		if (banner.getImage() != null) {
			existingBanner.setImage(banner.getImage());
		}
		if (banner.getHeadLine() != null) {
			existingBanner.setHeadLine(banner.getHeadLine());
		}
		if (banner.getSubHeadline() != null) {
			existingBanner.setSubHeadline(banner.getSubHeadline());
		}
		return heroBannerRepository.save(existingBanner);
	}

	public void deleteBanner(int id) {
		if(!heroBannerRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		heroBannerRepository.deleteById(id);
	}
}
