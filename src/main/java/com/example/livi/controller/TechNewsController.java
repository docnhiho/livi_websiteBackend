package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.TechNews;
import com.example.livi.service.TechNewsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/technews")
public class TechNewsController {

	@Autowired
	private TechNewsService techNewsService;
	
	@GetMapping("")
	public List<TechNews> getTechNews() {
		return techNewsService.getTechNews();
	}
	
	@GetMapping("/{id}")
	public TechNews gettTechNewsbyId(@PathVariable int id) {
		return techNewsService.gettNewsById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			TechNews techNews = new TechNews();
			techNews.setName(name);
			techNews.setDescription(description);

			TechNews savedEntity = techNewsService.addTechNews(techNews, sessionId,
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
			TechNews techNews = new TechNews();
			techNews.setDescription(description);
			techNews.setName(name);
			TechNews updatedEntity = techNewsService.updateTechNews(id, techNews, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechNews(@PathVariable int id) {
		techNewsService.deleteTechNews(id);
	}
	
}
