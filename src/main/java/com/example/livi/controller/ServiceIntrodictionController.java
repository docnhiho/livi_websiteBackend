package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.ServiceIntroduction;
import com.example.livi.service.ServiceIntrodictionService;

@RestController
@RequestMapping("/serviceintroduction")
public class ServiceIntrodictionController {

	@Autowired
	private ServiceIntrodictionService serviceIntrodictionService;
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@GetMapping("")
	public List<ServiceIntroduction> getServiceIntroductions() {
		return serviceIntrodictionService.getServiceIntroductions();
	}

	@GetMapping("/{id}")
	public ServiceIntroduction getServiceIntroductionById(@PathVariable int id) {
		return serviceIntrodictionService.getServiceIntroductionById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("icon_display") MultipartFile icon,
			@RequestParam("headline") String headline,
			@RequestParam("description") String description,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = icon.getBytes();
			ServiceIntroduction serviceIntroduction = new ServiceIntroduction();
			serviceIntroduction.setHeadline(headline);
			serviceIntroduction.setDescription(description);
			ServiceIntroduction savedEntity = serviceIntrodictionService.addServiceIntroduction(serviceIntroduction, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "icon_display", required = false) MultipartFile icon,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "description", required = false) String description,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (icon != null) {
				fileBytes = icon.getBytes();
			}
			ServiceIntroduction serviceIntroduction = new ServiceIntroduction();
			serviceIntroduction.setDescription(description);
			serviceIntroduction.setHeadline(headline);
			ServiceIntroduction updatedEntity = serviceIntrodictionService.updateServiceIntroduction(id, serviceIntroduction, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		serviceIntrodictionService.deleteServiceIntroduction(id);
	}
}
