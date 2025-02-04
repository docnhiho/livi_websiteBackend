package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.livi.model.TechAppliedTech;
import com.example.livi.service.TechAppliedTechService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/techappliedtech")
public class TechAppliedTechController {
	
	@Autowired
	private TechAppliedTechService techAppliedTechService;
	
	@GetMapping("")
	public List<TechAppliedTech> geTechAppliedTech() {
		return techAppliedTechService.geTechAppliedTech();
	}
	
	@GetMapping("/{id}")
	public TechAppliedTech getTechAppliedTechById(@PathVariable int id) {
		return techAppliedTechService.getTechAppliedTechById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("name") String name, 
			@RequestParam("description") String description,
			@RequestParam("lang") String lang,

			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			TechAppliedTech techAppliedTech = new TechAppliedTech();
			techAppliedTech.setName(name);
			techAppliedTech.setDescription(description);
			techAppliedTech.setLang(lang);
			TechAppliedTech savedEntity = techAppliedTechService.addTechAppliedTech(techAppliedTech, sessionId,
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
			@RequestParam(value = "lang", required = false) String lang,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (thumbnail != null) {
				fileBytes = thumbnail.getBytes();
			}
			TechAppliedTech techAppliedTech = new TechAppliedTech();
			techAppliedTech.setDescription(description);
			techAppliedTech.setName(name);
			techAppliedTech.setLang(lang);
			TechAppliedTech updatedEntity = techAppliedTechService.updateTechAppliedTech(id, techAppliedTech, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechAppliedTech(@PathVariable int id) {
		techAppliedTechService.deleteTechAppliedTech(id);
	}

}
