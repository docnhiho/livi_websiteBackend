package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceKeyFeatures;
import com.example.livi.model.Session;
import com.example.livi.repository.ServiceKeyFeaturesRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ServiceKeyFeaturesService {

	@Autowired
	private ServiceKeyFeaturesRepository serviceKeyFeaturesRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<ServiceKeyFeatures> getServiceKeyFeatures() {
		return serviceKeyFeaturesRepository.findAll();
	}

	public ServiceKeyFeatures getServiceKeyFeaturesById(int id) {
		return serviceKeyFeaturesRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public ServiceKeyFeatures addServiceKeyFeatures(ServiceKeyFeatures serviceKeyFeatures, int sessionId) {
		if (serviceKeyFeatures != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceKeyFeatures.setSession(session);
			return serviceKeyFeaturesRepository.save(serviceKeyFeatures);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public ServiceKeyFeatures updateServiceKeyFeatures(int id, ServiceKeyFeatures serviceKeyFeatures) {
		ServiceKeyFeatures serviceKeyFeatures2 = getServiceKeyFeaturesById(id);
		if (serviceKeyFeatures.getThumbnail() != null) {
			serviceKeyFeatures2.setThumbnail(serviceKeyFeatures.getThumbnail());
		}
		if (serviceKeyFeatures.getName() != null) {
			serviceKeyFeatures2.setName(serviceKeyFeatures.getName());
		}
		if (serviceKeyFeatures.getLink() != null) {
			serviceKeyFeatures2.setLink(serviceKeyFeatures.getLink());
		}
		return serviceKeyFeaturesRepository.save(serviceKeyFeatures2);
	}

	public void deleteServiceKeyFeatures(int id) {
		if (!serviceKeyFeaturesRepository.existsById(id)) {
			throw new RuntimeException("ServiceKeyFeatures not found with id: " + id);
		}
		serviceKeyFeaturesRepository.deleteById(id);
	}
}
