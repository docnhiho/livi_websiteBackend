package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.livi.model.ServiceKeyFeatures;
import com.example.livi.model.Section;
import com.example.livi.repository.ServiceKeyFeaturesRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class ServiceKeyFeaturesService {

	@Autowired
	private ServiceKeyFeaturesRepository serviceKeyFeaturesRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public List<ServiceKeyFeatures> getServiceKeyFeatures() {
		return serviceKeyFeaturesRepository.findAll();
	}

	public ServiceKeyFeatures getServiceKeyFeaturesById(int id) {
		return serviceKeyFeaturesRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public ServiceKeyFeatures addServiceKeyFeatures(ServiceKeyFeatures serviceKeyFeatures, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		serviceKeyFeatures.setSession(session);
		serviceKeyFeatures.setThumbnail(base64Image);

		return serviceKeyFeaturesRepository.save(serviceKeyFeatures);
	}

	public ServiceKeyFeatures updateServiceKeyFeatures(int id, ServiceKeyFeatures serviceKeyFeatures, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<ServiceKeyFeatures> optional = serviceKeyFeaturesRepository.findById(id);
		if (optional.isPresent()) {
			ServiceKeyFeatures existingEntity = optional.get();
			if (serviceKeyFeatures.getName() != null) {
				existingEntity.setName(serviceKeyFeatures.getName());
			}
			if (serviceKeyFeatures.getLink() != null) {
				existingEntity.setLink(serviceKeyFeatures.getLink());
			}
			if (serviceKeyFeatures.getButtonText() != null) {
				existingEntity.setButtonText(serviceKeyFeatures.getButtonText());
			}
			if (serviceKeyFeatures.getLang() != null) {
				existingEntity.setLang(serviceKeyFeatures.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return serviceKeyFeaturesRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}

	public void deleteServiceKeyFeatures(int id) {
		if (!serviceKeyFeaturesRepository.existsById(id)) {
			throw new RuntimeException("ServiceKeyFeatures not found with id: " + id);
		}
		serviceKeyFeaturesRepository.deleteById(id);
	}
}
