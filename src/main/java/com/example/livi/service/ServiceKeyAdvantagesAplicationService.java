package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceKeyAdvantagesAplication;
import com.example.livi.model.Session;
import com.example.livi.repository.ServiceKeyAdvantagesAplicationRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ServiceKeyAdvantagesAplicationService {

	@Autowired
	private ServiceKeyAdvantagesAplicationRepository serviceKeyAdvantagesAplicationRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<ServiceKeyAdvantagesAplication> getServiceKeyAdvantagesAplication() {
		return serviceKeyAdvantagesAplicationRepository.findAll();
	}

	public ServiceKeyAdvantagesAplication getServiceKeyAdvantagesAplicationById(int id) {
		return serviceKeyAdvantagesAplicationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public ServiceKeyAdvantagesAplication addServiceKeyAdvantagesAplication(
			ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication, int sessionId) {
		if (serviceKeyAdvantagesAplication != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceKeyAdvantagesAplication.setSession(session);
			return serviceKeyAdvantagesAplicationRepository.save(serviceKeyAdvantagesAplication);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public ServiceKeyAdvantagesAplication updateServiceKeyAdvantagesAplication(int id,
			ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication) {
		ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication2 = getServiceKeyAdvantagesAplicationById(id);
		if (serviceKeyAdvantagesAplication.getThumbnail() != null) {
			serviceKeyAdvantagesAplication2.setThumbnail(serviceKeyAdvantagesAplication.getThumbnail());
		}
		if (serviceKeyAdvantagesAplication.getName() != null) {
			serviceKeyAdvantagesAplication2.setName(serviceKeyAdvantagesAplication.getName());
		}
		if (serviceKeyAdvantagesAplication.getLink() != null) {
			serviceKeyAdvantagesAplication2.setLink(serviceKeyAdvantagesAplication.getLink());
		}
		return serviceKeyAdvantagesAplicationRepository.save(serviceKeyAdvantagesAplication2);
	}

	public void deleteServiceKeyAdvantagesAplication(int id) {
		if (!serviceKeyAdvantagesAplicationRepository.existsById(id)) {
			throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceKeyAdvantagesAplicationRepository.deleteById(id);
	}
}
