package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Section;
import com.example.livi.model.TechListing;
import com.example.livi.repository.SectionRepository;
import com.example.livi.repository.TechListingRepository;

@Service
public class TechListingService {

	@Autowired
	private TechListingRepository techListingRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public List<TechListing> geTechListing(){
		return techListingRepository.findAll();
	}
	
	public TechListing geTechListingById(int id) {
		return techListingRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public TechListing addListing(TechListing techListing, int sessionId) {
		if (techListing != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			techListing.setSession(session);
			return techListingRepository.save(techListing);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public TechListing updateTechListing(int id, TechListing techListing) {
		TechListing techListing2 = geTechListingById(id);
		
		if(techListing.getTechnologyName() != null) {
			techListing2.setTechnologyName(techListing.getTechnologyName());
		}
		
		if(techListing.getLink() != null) {
			techListing2.setLink(techListing.getLink());
		}
		
		if(techListing.getMetaDescription() != null) {
			techListing2.setMetaDescription(techListing.getMetaDescription());
		}
		
		if(techListing.getCreatedBy() != null) {
			techListing2.setCreatedBy(techListing.getCreatedBy());
		}
		if(techListing.getCreatedDate() != null) {
			techListing2.setCreatedDate(techListing.getCreatedDate());
		}
		if(techListing.getLang() != null) {
			techListing2.setLang(techListing.getLang());
		}
		return techListingRepository.save(techListing2);
	}
	
	public void deleteTechnologyListing(int id) {
		if(!techListingRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		techListingRepository.deleteById(id);
	}
}
