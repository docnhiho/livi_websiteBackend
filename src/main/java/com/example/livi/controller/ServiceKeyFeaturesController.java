package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.ServiceKeyFeatures;
import com.example.livi.model.Session;
import com.example.livi.service.ServiceKeyFeaturesService;

@RestController
@RequestMapping("/servicekeyfeatures")
public class ServiceKeyFeaturesController {
	@Autowired
	private ServiceKeyFeaturesService serviceKeyFeatures;
	
	@GetMapping("")
	public List<ServiceKeyFeatures> getKeyFeatures() {
		return serviceKeyFeatures.getServiceKeyFeatures();
	}
	
	@GetMapping("{id}")
	public ServiceKeyFeatures getServiceKeyFeaturesById(@PathVariable int id) {
		return serviceKeyFeatures.getServiceKeyFeaturesById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("name") String name,
			@RequestParam("link") String link, 
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			ServiceKeyFeatures serviceKeyFeature = new ServiceKeyFeatures();
	
			serviceKeyFeature.setName(name);
			serviceKeyFeature.setLink(link);
			ServiceKeyFeatures savedEntity = serviceKeyFeatures.addServiceKeyFeatures(serviceKeyFeature, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "link", required = false) String link, @PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (thumbnail != null) {
				fileBytes = thumbnail.getBytes();
			}
			ServiceKeyFeatures serviceKeyFeature = new ServiceKeyFeatures();
			serviceKeyFeature.setName(name);

			serviceKeyFeature.setLink(link);
			ServiceKeyFeatures updatedEntity = serviceKeyFeatures.updateServiceKeyFeatures(id, serviceKeyFeature, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		serviceKeyFeatures.deleteServiceKeyFeatures(id);
	}
}
