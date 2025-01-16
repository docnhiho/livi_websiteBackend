package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.TechKeyCapability;
import com.example.livi.service.TechKeyCapabilityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/techkeycapability")
public class TechKeyCapabilityController {
	
	@Autowired
	private TechKeyCapabilityService techKeyCapabilityService;
	
	@GetMapping("")
	public List<TechKeyCapability> getTechKeyCapabilities(){
		return techKeyCapabilityService.getTechKeyCapabilities();
	}
	
	@GetMapping("/{id}")
	public TechKeyCapability getTechKeyCapabilityById(@PathVariable int id) {
		return techKeyCapabilityService.getTechKeyCapabilityById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("headline") String headline, 
			@RequestParam("description") String description,
			@RequestParam("Button_Text") String buttonText,
			@RequestParam("link") String link,

			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			TechKeyCapability techKeyCapability = new TechKeyCapability();
			techKeyCapability.setHeadline(headline);
			techKeyCapability.setDescription(description);
			techKeyCapability.setLink(link);
			techKeyCapability.setButtonText(buttonText);
			TechKeyCapability savedEntity = techKeyCapabilityService.addTechKeyCapability(techKeyCapability, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "link", required = false) String link, 
			@RequestParam(value = "Button_Text", required = false) String buttonText, 

			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (thumbnail != null) {
				fileBytes = thumbnail.getBytes();
			}
			TechKeyCapability techKeyCapability = new TechKeyCapability();
			techKeyCapability.setDescription(description);
			techKeyCapability.setHeadline(headline);
			techKeyCapability.setLink(link);
			techKeyCapability.setButtonText(buttonText);
			TechKeyCapability updatedEntity = techKeyCapabilityService.updateTechKeyCapability(id, techKeyCapability, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechKeyCapability(@PathVariable int id) {
		techKeyCapabilityService.deleteTechKeyCapability(id);
	}
}
