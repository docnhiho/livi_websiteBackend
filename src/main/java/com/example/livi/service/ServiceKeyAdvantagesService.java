package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceKeyAdvantages;
import com.example.livi.model.Session;
import com.example.livi.repository.ServiceKeyAdvantagesRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ServiceKeyAdvantagesService {

	@Autowired
	private ServiceKeyAdvantagesRepository serviceKeyAdvantagesRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<ServiceKeyAdvantages> getServiceKeyAdvantages(){
		return serviceKeyAdvantagesRepository.findAll();
	}
	
	public ServiceKeyAdvantages getServiceKeyAdvantagesById(int id) {
		return serviceKeyAdvantagesRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public ServiceKeyAdvantages addServiceKeyAdvantages(ServiceKeyAdvantages serviceKeyAdvantages, int sessionId) {
		if (serviceKeyAdvantages != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceKeyAdvantages.setSession(session);
			return serviceKeyAdvantagesRepository.save(serviceKeyAdvantages);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public ServiceKeyAdvantages updateServiceKeyAdvantages(int id, ServiceKeyAdvantages serviceKeyAdvantages) {
		ServiceKeyAdvantages serviceKeyAdvantages2 = getServiceKeyAdvantagesById(id);
		if(serviceKeyAdvantages.getHeadLine() != null) {
			serviceKeyAdvantages2.setHeadLine(serviceKeyAdvantages.getHeadLine());
		}
		if(serviceKeyAdvantages.getDescription() != null) {
			serviceKeyAdvantages2.setHeadLine(serviceKeyAdvantages.getDescription());
		}
		return serviceKeyAdvantagesRepository.save(serviceKeyAdvantages2);
	}
	
	public void deleteServiceKeyAdvantages(int id) {
		if(!serviceKeyAdvantagesRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceKeyAdvantagesRepository.deleteById(id);
	}
}

