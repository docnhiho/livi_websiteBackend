package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.TechListing;
import com.example.livi.repository.TechListingRepository;
import com.example.livi.service.TechListingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/testlisting")
public class TechListingController {

	@Autowired
	private TechListingService techListingService;
	
	@GetMapping("")
	public List<TechListing> geTechListings() {
		return techListingService.geTechListing();
	}
	
	@GetMapping("/{id}")
	public TechListing geTechListingById(@PathVariable int id) {
		return techListingService.geTechListingById(id);
	}
	
	
	@PostMapping("/{sessionId}")
	public TechListing addTechListing(@RequestBody TechListing techListing, @PathVariable int sessionId) {
		return techListingService.addListing(techListing, sessionId);
	}
	
	@PutMapping("/{id}")
	public TechListing techListing(@PathVariable int id, @RequestBody TechListing techListing) {
		//TODO: process PUT request
		
		return techListingService.updateTechListing(id, techListing);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechListing(@PathVariable int id) {
		techListingService.deleteTechnologyListing(id);
	}
}
