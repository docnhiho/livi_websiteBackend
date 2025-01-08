package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.ServiceDocumentBanner;
import com.example.livi.service.ServiceDocumentBannerService;

@RestController
@RequestMapping("/servicedocumentbanner")
public class ServiceDocumentBannerController {

	@Autowired
	private ServiceDocumentBannerService serviceDocumentBannerService;
	
	@GetMapping("")
	public List<ServiceDocumentBanner> getServiceDocumentBanners() {
		return serviceDocumentBannerService.getServiceDocumentBanner();
	}
	
	@GetMapping("{id}")
	public ServiceDocumentBanner getServiceDocumentBannerById(@PathVariable int id) {
		return serviceDocumentBannerService.getServiceDocumentBannerById(id);
	}
	
	@PostMapping("{sessionId}")
	public ServiceDocumentBanner addServiceDocumentBanner(@RequestBody ServiceDocumentBanner serviceDocumentBanner, @PathVariable int sessionId) {
		//TODO: process POST request
		return serviceDocumentBannerService.addServiceDocumentBanner(serviceDocumentBanner, sessionId);
	}
	
	@PutMapping("/{id}")
	public ServiceDocumentBanner updatServiceDocumentBanner(@PathVariable int id, @RequestBody ServiceDocumentBanner serviceDocumentBanner) {
		return serviceDocumentBannerService.updateServiceDocumentBanner(id, serviceDocumentBanner);
	}
	
	@DeleteMapping("/{id}")
	public void deleteServiceDocumentBannerService(@PathVariable int id) {
		serviceDocumentBannerService.deleteServiceDocumentBanner(id);
	}
}
