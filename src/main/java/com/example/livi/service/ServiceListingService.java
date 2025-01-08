package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceListing;
import com.example.livi.model.Session;
import com.example.livi.repository.ServiceListingRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ServiceListingService {

	@Autowired
	private ServiceListingRepository serviceListingRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<ServiceListing> getServiceListing() {
		return serviceListingRepository.findAll();
	}

	public ServiceListing getServiceListingById(int id) {
		return serviceListingRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public ServiceListing addServiceListing(ServiceListing serviceListing, int sessionId) {
		if (serviceListing != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceListing.setSession(session);
			return serviceListingRepository.save(serviceListing);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public ServiceListing updatetServiceListing(int id, ServiceListing serviceListing) {
		ServiceListing serviceListing2 = getServiceListingById(id);
		if(serviceListing.getName() != null) {
			serviceListing2.setName(serviceListing.getName());
		}
		if(serviceListing.getLink() != null) {
			serviceListing2.setLink(serviceListing.getLink());
		}
		if(serviceListing.getDescription() != null) {
			serviceListing2.setDescription(serviceListing.getDescription());
		}
		if(serviceListing.getCreateBy() != null) {
			serviceListing2.setDescription(serviceListing.getDescription());
		}
		if(serviceListing.getName() != null) {
			serviceListing2.setCreateDate(serviceListing.getCreateDate());
		}
		return serviceListingRepository.save(serviceListing2);
	}
	
	public void deleteServiceListing(int id) {
		if(!serviceListingRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceListingRepository.deleteById(id);
	}
}
