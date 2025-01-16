package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.livi.model.TechIntroduction;
import com.example.livi.service.TechIntroductionService;

@RestController
@RequestMapping("/techintroduction")
public class TechIntroductionController {
	@Autowired
	private TechIntroductionService techIntroductionService;

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@GetMapping("")
	public List<TechIntroduction> getTechIntroductions() {
		return techIntroductionService.getTechIntroductions();
	}

	@GetMapping("/{id}")
	public TechIntroduction gettTechIntroductionById(@PathVariable int id) {
		return techIntroductionService.gettTechIntroductionById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("icon_display") MultipartFile iconDisplay,
			@RequestParam("headline") String headline, 
			@RequestParam("description") String description,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = iconDisplay.getBytes();
			TechIntroduction techIntroduction = new TechIntroduction();
			techIntroduction.setHeadline(headline);
			techIntroduction.setDescription(description);
			TechIntroduction savedEntity = techIntroductionService.addTechIntroduction(techIntroduction, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "icon_display", required = false) MultipartFile iconDisplay,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "description", required = false) String description,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (iconDisplay != null) {
				fileBytes = iconDisplay.getBytes();
			}
			TechIntroduction techIntroduction = new TechIntroduction();
			techIntroduction.setDescription(description);
			techIntroduction.setHeadline(headline);
			TechIntroduction updatedEntity = techIntroductionService.updateTechIntroduction(id, techIntroduction, fileBytes);
			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
