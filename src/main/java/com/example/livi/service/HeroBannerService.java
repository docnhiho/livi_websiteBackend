package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.HeroBanner;
import com.example.livi.model.Page;
import com.example.livi.model.Section;
import com.example.livi.repository.HeroBannerRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class HeroBannerService {

	@Autowired
	private HeroBannerRepository heroBannerRepository;
	@Autowired
	private SectionRepository sessionRepository;

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

	public HeroBanner addSBanner(HeroBanner heroBanner, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		heroBanner.setSession(session);
		heroBanner.setImage(base64Image);

		return heroBannerRepository.save(heroBanner);
	}

	public HeroBanner updateBanner(int id, HeroBanner banner, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<HeroBanner> optional = heroBannerRepository.findById(id);
		if (optional.isPresent()) {
			HeroBanner existingEntity = optional.get();
			if (banner.getHeadLine() != null) {
				existingEntity.setHeadLine(banner.getHeadLine());
			}
			if (banner.getSubHeadline() != null) {
				existingEntity.setSubHeadline(banner.getSubHeadline());
			}
			if (banner.getLink() != null) {
				existingEntity.setLink(banner.getLink());
			}
			if (banner.getButtonText() != null) {
				existingEntity.setButtonText(banner.getButtonText());
			}
			if (banner.getLang() != null) {
				existingEntity.setLang(banner.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getImage())) {
				existingEntity.setImage(base64Image);
			}
			return heroBannerRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("HeroBanner not found with ID " + id);
		}
	}

	public void deleteBanner(int id) {
		if(!heroBannerRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		heroBannerRepository.deleteById(id);
	}
}
