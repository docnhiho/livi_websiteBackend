package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.AboutLiviLife;
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
			ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		serviceKeyAdvantagesAplication.setSession(session);
		serviceKeyAdvantagesAplication.setThumbnail(base64Image);

		return serviceKeyAdvantagesAplicationRepository.save(serviceKeyAdvantagesAplication);
	}

	public ServiceKeyAdvantagesAplication updateServiceKeyAdvantagesAplication(int id,
			ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<ServiceKeyAdvantagesAplication> optional = serviceKeyAdvantagesAplicationRepository.findById(id);
		if (optional.isPresent()) {
			ServiceKeyAdvantagesAplication existingEntity = optional.get();
			if (serviceKeyAdvantagesAplication.getName() != null) {
				existingEntity.setName(serviceKeyAdvantagesAplication.getName());
			}
			if (serviceKeyAdvantagesAplication.getLink() != null) {
				existingEntity.setLink(serviceKeyAdvantagesAplication.getLink());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return serviceKeyAdvantagesAplicationRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("ServiceKeyAdvantagesAplication not found with ID " + id);
		}
	}

	public void deleteServiceKeyAdvantagesAplication(int id) {
		if (!serviceKeyAdvantagesAplicationRepository.existsById(id)) {
			throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceKeyAdvantagesAplicationRepository.deleteById(id);
	}
}
