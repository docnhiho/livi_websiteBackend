package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ServiceMoreInfo;
import com.example.livi.model.Session;
import com.example.livi.repository.ServiceMoreInfoRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ServiceMoreInfoSerivce {
	@Autowired
	private ServiceMoreInfoRepository serviceMoreInfoRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<ServiceMoreInfo> getServiceMoreInfo(){
		return serviceMoreInfoRepository.findAll();
	}
	
	public ServiceMoreInfo getServiceMoreInfoById(int id) {
		return serviceMoreInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public ServiceMoreInfo addServiceMoreInfo(ServiceMoreInfo serviceMoreInfo, int sessionId) {
		if (serviceMoreInfo != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			serviceMoreInfo.setSession(session);
			return serviceMoreInfoRepository.save(serviceMoreInfo);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public ServiceMoreInfo updateServiceMoreInfo(int id, ServiceMoreInfo serviceMoreInfo) {
		ServiceMoreInfo serviceMoreInfo2 =getServiceMoreInfoById(id);
		if(serviceMoreInfo.getThumbnail() != null) {
			serviceMoreInfo2.setThumbnail(serviceMoreInfo.getThumbnail());
		}
		if(serviceMoreInfo.getHeadline() != null) {
			serviceMoreInfo2.setHeadline(serviceMoreInfo.getHeadline());
		}
		if(serviceMoreInfo.getThumbnail() != null) {
			serviceMoreInfo2.setSubHeadline(serviceMoreInfo.getSubHeadline());
		}
		return serviceMoreInfoRepository.save(serviceMoreInfo2);
	}
	
	public void deleteServiceMoreInfo(int id) {
		if(!serviceMoreInfoRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		serviceMoreInfoRepository.deleteById(id);
	}

}
