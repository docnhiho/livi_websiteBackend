package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.HomelistClient;
import com.example.livi.model.Section;
import com.example.livi.repository.HomelistClientRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class HomelistClientService {
	@Autowired
	private HomelistClientRepository homelistClientRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public List<HomelistClient> getList() {
		return homelistClientRepository.findAll();
	}

	public HomelistClient getHomelistClientById(int id) {
		return homelistClientRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public HomelistClient addHomelistClient(HomelistClient homelistClient, int sessionId) {
		if (homelistClient != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			homelistClient.setSession(session);
			return homelistClientRepository.save(homelistClient);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public HomelistClient updateHomelistClient(int id, HomelistClient homelistClient) {
		HomelistClient homelistClient2 = getHomelistClientById(id);

		if (homelistClient.getClientBrandList() != null) {
			homelistClient2.setClientBrandList(homelistClient.getClientBrandList());
		}

		return homelistClientRepository.save(homelistClient2);
	}

	public void deleteHomelistClient(int id) {
		if(!homelistClientRepository.existsById(id)) {
	        throw new RuntimeException("HomelistClient not found with id: " + id);
		}
		homelistClientRepository.deleteById(id);
	}
}
