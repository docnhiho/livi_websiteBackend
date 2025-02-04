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
import com.example.livi.model.TechAward;
import com.example.livi.service.TechAwardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/techaward")
public class TechAwardController {

	@Autowired
	private TechAwardService techAwardService;
	
	@GetMapping("")
	public List<TechAward> getTechAwards(){
		return techAwardService.geTechAwards();
	}
	
	@GetMapping("/{id}")
	public TechAward gettAwardServiceById(@PathVariable int id) {
		return techAwardService.getTechAwardById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("name") String name, 
			@RequestParam("description") String description,
			@RequestParam("lang") String lang,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			TechAward techAward = new TechAward();
			techAward.setName(name);
			techAward.setDescription(description);
			techAward.setLang(lang);
			TechAward savedEntity = techAwardService.addTechAward(techAward, sessionId,
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
			TechAward techAward = new TechAward();
			techAward.setDescription(description);
			techAward.setName(name);
			techAward.setLang(lang);
			TechAward updatedEntity = techAwardService.updateTechAward(id, techAward, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechAward(@PathVariable int id) {
		techAwardService.deleteTechAward(id);
	}
}
