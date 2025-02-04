package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.HeroBanner;
import com.example.livi.model.HomeListService;
import com.example.livi.model.Section;
import com.example.livi.repository.HeroBannerRepository;
import com.example.livi.repository.HomeListServiceRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class HomeListServiceService {
	@Autowired
	private HomeListServiceRepository homeListServiceRepository;
	@Autowired
	private SectionRepository sessionRepository;


	public List<HomeListService> getHomeListServices() {
		return homeListServiceRepository.findAll();
	}

	public HomeListService getHomeListServiceById(int id) {
		return homeListServiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public HomeListService addHomeListService(HomeListService homeListService, int sessionId) {
		if (homeListService != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			homeListService.setSession(session);
			return homeListServiceRepository.save(homeListService);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public HomeListService updateHomeListService(int id, HomeListService homeListService) {
		HomeListService homeListService2 = getHomeListServiceById(id);

		if (homeListService.getServiceListing() != null) {
			homeListService2.setServiceListing(homeListService.getServiceListing());
		}
		if (homeListService.getHeroBanner() != null) {
			homeListService2.setHeroBanner(homeListService.getHeroBanner());
		}
		if (homeListService.getMediaList() != null) {
			homeListService2.setMediaList(homeListService.getMediaList());
		}
		return homeListServiceRepository.save(homeListService2);
	}

	public void deleteHomeListService(int id) {
		if(!homeListServiceRepository.existsById(id)) {
	        throw new RuntimeException("HomeListService not found with id: " + id);
		}
		homeListServiceRepository.deleteById(id);
	}
}
