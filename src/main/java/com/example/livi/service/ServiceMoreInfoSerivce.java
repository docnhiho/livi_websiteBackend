package com.example.livi.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceMoreInfo;
import com.example.livi.model.Section;
import com.example.livi.repository.ServiceMoreInfoRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class ServiceMoreInfoSerivce {
	@Autowired
	private ServiceMoreInfoRepository serviceMoreInfoRepository;
	@Autowired
	private SectionRepository sessionRepository;
	
	public List<ServiceMoreInfo> getServiceMoreInfo(){
		return serviceMoreInfoRepository.findAll();
	}
	
	public ServiceMoreInfo getServiceMoreInfoById(int id) {
		return serviceMoreInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public ServiceMoreInfo addServiceMoreInfo(ServiceMoreInfo serviceMoreInfo, int sessionId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Section session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		serviceMoreInfo.setSession(session);
		serviceMoreInfo.setThumbnail(base64Image);

		return serviceMoreInfoRepository.save(serviceMoreInfo);
	}
	
	public ServiceMoreInfo updateServiceMoreInfo(int id, ServiceMoreInfo serviceMoreInfo, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<ServiceMoreInfo> optional = serviceMoreInfoRepository.findById(id);
		if (optional.isPresent()) {
			ServiceMoreInfo existingEntity = optional.get();
			if (serviceMoreInfo.getHeadline() != null) {
				existingEntity.setHeadline(serviceMoreInfo.getHeadline());
			}
			if (serviceMoreInfo.getSubHeadline() != null) {
				existingEntity.setSubHeadline(serviceMoreInfo.getSubHeadline());
			}
			if (serviceMoreInfo.getLang() != null) {
				existingEntity.setLang(serviceMoreInfo.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getThumbnail())) {
				existingEntity.setThumbnail(base64Image);
			}
			return serviceMoreInfoRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}
	
	public void deleteServiceMoreInfo(int id) {
		if(!serviceMoreInfoRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceMoreInfoRepository.deleteById(id);
	}

}
