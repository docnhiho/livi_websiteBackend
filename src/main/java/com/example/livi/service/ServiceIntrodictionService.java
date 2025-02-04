package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.livi.model.ServiceIntroduction;
import com.example.livi.model.Section;
import com.example.livi.repository.ServiceIntrodictionRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class ServiceIntrodictionService {
	@Autowired
	private ServiceIntrodictionRepository serviceIntrodictionRepository;
	@Autowired
	private SectionRepository sessionRepository;
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public List<ServiceIntroduction> getServiceIntroductions(){
		return serviceIntrodictionRepository.findAll();
	}
	
	public ServiceIntroduction getServiceIntroductionById(int id) {
		return serviceIntrodictionRepository.findById(id).orElseThrow(() -> new RuntimeException("ServiceIntroduction not found"));
	}
	
	public ServiceIntroduction addServiceIntroduction(ServiceIntroduction serviceIntroduction, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		serviceIntroduction.setSession(session);
		serviceIntroduction.setIcon(base64Image);

		return serviceIntrodictionRepository.save(serviceIntroduction);
	}
	
    public ServiceIntroduction updateServiceIntroduction(int id, ServiceIntroduction serviceIntroduction, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<ServiceIntroduction> optional = serviceIntrodictionRepository.findById(id);
		if (optional.isPresent()) {
			ServiceIntroduction existingEntity = optional.get();
			if (serviceIntroduction.getHeadline() != null) {
				existingEntity.setHeadline(serviceIntroduction.getHeadline());
			}
			if (serviceIntroduction.getDescription() != null) {
				existingEntity.setDescription(serviceIntroduction.getDescription());
			}		
			if (serviceIntroduction.getLang() != null) {
				existingEntity.setLang(serviceIntroduction.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getIcon())) {
				existingEntity.setIcon(base64Image);
			}
			return serviceIntrodictionRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
    }
    
	public void deleteServiceIntroduction(int id) {
		if (!serviceIntrodictionRepository.existsById(id)) {
			throw new RuntimeException("AboutLiviLife not found with id: " + id);
		}
		serviceIntrodictionRepository.deleteById(id);
	}
}
