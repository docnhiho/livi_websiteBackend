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
import com.example.livi.model.AboutRelatedNew;
import com.example.livi.model.Session;
import com.example.livi.service.AboutRelatedNewService;

@RestController
@RequestMapping("/aboutrelatednew")
public class AboutRelatedNewController {

	@Autowired
	private AboutRelatedNewService aboutRelatedNewService;
	
	@GetMapping("")
	public List<AboutRelatedNew> getAllSessions() {
		return aboutRelatedNewService.getAboutRelatedNews();
	}
	
	@GetMapping("{id}")
	public AboutRelatedNew getSessionById(@PathVariable int id) {
		return aboutRelatedNewService.getAboutRelatedNewById(id);
	}
	
	@PostMapping("{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("name") String name, @RequestParam("description") String description,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			AboutRelatedNew aboutRelatedNew = new AboutRelatedNew();
			aboutRelatedNew.setDescription(description);
			aboutRelatedNew.setName(name);
			AboutRelatedNew savedEntity = aboutRelatedNewService.addAboutRelatedNew(aboutRelatedNew, sessionId,
					fileBytes);

			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (thumbnail != null) {
				fileBytes = thumbnail.getBytes();
			}
			AboutRelatedNew aboutRelatedNew = new AboutRelatedNew();
			aboutRelatedNew.setDescription(description);
			aboutRelatedNew.setName(name);
			AboutRelatedNew updatedEntity = aboutRelatedNewService.update(id, aboutRelatedNew, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@DeleteMapping("/{id}")
	public void deleteAboutRelatedNew(@PathVariable int id) {
		aboutRelatedNewService.delete(id);
	}
}
