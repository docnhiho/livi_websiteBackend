package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceDevelopmentProcess;
import com.example.livi.model.Section;
import com.example.livi.repository.ServiceDevelopmentProcessRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class ServiceDevelopmentProcessService {

	@Autowired
	private ServiceDevelopmentProcessRepository serviceDevelopmentProcessRepository;
	@Autowired
	private SectionRepository sessionRepository;
	
	public List<ServiceDevelopmentProcess> getServiceDevelopmentProcesses(){
		return serviceDevelopmentProcessRepository.findAll();
	}
	
	public ServiceDevelopmentProcess getServiceDevelopmentProcessById(int id) {
		return serviceDevelopmentProcessRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public ServiceDevelopmentProcess addServiceDevelopmentProcess(ServiceDevelopmentProcess serviceDevelopmentProcess, int sessionId) {
		if (serviceDevelopmentProcess != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceDevelopmentProcess.setSession(session);
			return serviceDevelopmentProcessRepository.save(serviceDevelopmentProcess);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public ServiceDevelopmentProcess updateServiceDevelopmentProcess(int id, ServiceDevelopmentProcess serviceDevelopmentProcess) {
		ServiceDevelopmentProcess serviceDevelopmentProcess2 = getServiceDevelopmentProcessById(id);
		if(serviceDevelopmentProcess.getHeadline() != null) {
			serviceDevelopmentProcess2.setHeadline(serviceDevelopmentProcess.getHeadline());
		}
		if(serviceDevelopmentProcess.getSubHeadline() != null) {
			serviceDevelopmentProcess2.setSubHeadline(serviceDevelopmentProcess.getSubHeadline());
		}
		if(serviceDevelopmentProcess.getLang() != null) {
			serviceDevelopmentProcess2.setLang(serviceDevelopmentProcess.getLang());
		}
		return serviceDevelopmentProcessRepository.save(serviceDevelopmentProcess2);
	}
	
	public void deleteServiceDevelopmentProcess(int id) {
		if(!serviceDevelopmentProcessRepository.existsById(id)) {
	        throw new RuntimeException("ServiceDevelopmentProcess not found with id: " + id);
		}
		serviceDevelopmentProcessRepository.deleteById(id);
	}
}
