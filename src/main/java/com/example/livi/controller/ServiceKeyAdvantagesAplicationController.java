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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.livi.model.ServiceKeyAdvantagesAplication;
import com.example.livi.service.ServiceKeyAdvantagesAplicationService;

@RestController
@RequestMapping("/servicekeyadvantagesaplication")
public class ServiceKeyAdvantagesAplicationController {
	
	@Autowired
	private ServiceKeyAdvantagesAplicationService serviceKeyAdvantagesAplicationService;
	
	@GetMapping("")
	public List<ServiceKeyAdvantagesAplication> getAdvantagesAplications() {
		return serviceKeyAdvantagesAplicationService.getServiceKeyAdvantagesAplication();
	}
	
	@GetMapping("{id}")
	public ServiceKeyAdvantagesAplication getSessionById(@PathVariable int id) {
		return serviceKeyAdvantagesAplicationService.getServiceKeyAdvantagesAplicationById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("name") String name, 
			@RequestParam("link") String link, 
			@RequestParam("lang") String lang, 
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication = new ServiceKeyAdvantagesAplication();
			serviceKeyAdvantagesAplication.setName(name);
			serviceKeyAdvantagesAplication.setLink(link);
			serviceKeyAdvantagesAplication.setLang(lang);
			ServiceKeyAdvantagesAplication savedEntity = serviceKeyAdvantagesAplicationService.addServiceKeyAdvantagesAplication(serviceKeyAdvantagesAplication, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "lang", required = false) String lang,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (thumbnail != null) {
				fileBytes = thumbnail.getBytes();
			}
			ServiceKeyAdvantagesAplication serviceKeyAdvantagesAplication = new ServiceKeyAdvantagesAplication();
			serviceKeyAdvantagesAplication.setName(name);
			serviceKeyAdvantagesAplication.setLink(link);
			serviceKeyAdvantagesAplication.setLang(lang);

			ServiceKeyAdvantagesAplication updatedEntity = serviceKeyAdvantagesAplicationService.updateServiceKeyAdvantagesAplication(id, serviceKeyAdvantagesAplication, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		serviceKeyAdvantagesAplicationService.deleteServiceKeyAdvantagesAplication(id);
	}
}
