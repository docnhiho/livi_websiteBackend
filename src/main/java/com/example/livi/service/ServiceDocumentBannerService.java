package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceDocumentBanner;
import com.example.livi.model.Section;
import com.example.livi.repository.ServiceDocumentBannerRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class ServiceDocumentBannerService {
	@Autowired
	private ServiceDocumentBannerRepository serviceDocumentBannerRepository;
	@Autowired
	private SectionRepository sessionRepository;
	
	public List<ServiceDocumentBanner> getServiceDocumentBanner(){
		return serviceDocumentBannerRepository.findAll();
	}
	
	public ServiceDocumentBanner getServiceDocumentBannerById(int id) {
		return serviceDocumentBannerRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public ServiceDocumentBanner addServiceDocumentBanner(ServiceDocumentBanner serviceDocumentBanner, int sessionId) {
		if (serviceDocumentBanner != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceDocumentBanner.setSession(session);
			return serviceDocumentBannerRepository.save(serviceDocumentBanner);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public ServiceDocumentBanner updateServiceDocumentBanner(int id, ServiceDocumentBanner serviceDocumentBanner) {
		ServiceDocumentBanner serviceDocumentBanner2 = getServiceDocumentBannerById(id);
		if(serviceDocumentBanner.getLanguageName() != null) {
			serviceDocumentBanner2.setLanguageName(serviceDocumentBanner.getLanguageName());
		}
		if(serviceDocumentBanner2.getAttachement() != null) {
			serviceDocumentBanner2.setAttachement(serviceDocumentBanner.getAttachement());
		}
		return serviceDocumentBannerRepository.save(serviceDocumentBanner2);
	}
	
	public void deleteServiceDocumentBanner(int id) {
		if(!serviceDocumentBannerRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceDocumentBannerRepository.deleteById(id);
	}
}
