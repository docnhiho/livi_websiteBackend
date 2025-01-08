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

import com.example.livi.model.ServiceListing;
import com.example.livi.service.ServiceListingService;

@RestController
@RequestMapping("/servicelisting")
public class ServiceListingController {

	@Autowired
	private ServiceListingService serviceListingService;
	
	@GetMapping("")
	public List<ServiceListing> getServiceListing(){
		return serviceListingService.getServiceListing();
	}
	
	@GetMapping("/{id}")
	public ServiceListing getServiceListingById(@PathVariable int id) {
		return serviceListingService.getServiceListingById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ServiceListing addServiceListing(@RequestBody ServiceListing serviceListing, @PathVariable int id) {
		//TODO: process POST request
		
		return serviceListingService.addServiceListing(serviceListing, id);
	}
	
	@PutMapping("/{id}")
	public ServiceListing updateServiceListing(@PathVariable int id, @RequestBody ServiceListing serviceListing) {
		//TODO: process PUT request
		
		return serviceListingService.updatetServiceListing(id, serviceListing);
	}
	
	@DeleteMapping("/{id}")
	public void deleteServiceListing(@PathVariable int id) {
		serviceListingService.deleteServiceListing(id);
	}
}
