package com.example.livi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.ScheduledTasksBeanDefinitionParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.HeroBanner;
import com.example.livi.model.Session;
import com.example.livi.service.HeroBannerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/banner")
public class HeroBannerController {

	@Autowired
	private HeroBannerService heroBannerService;

	@GetMapping("")
	public List<HeroBanner> getAllBanners() {
		return heroBannerService.getAllBanner();
	}
	
	@GetMapping("{id}")
	public HeroBanner getSessionById(@PathVariable int id) {
		return heroBannerService.getBannerById(id);
	}
	
	@PostMapping("{sessionId}")
	public ResponseEntity<?> add(@RequestParam("image") MultipartFile image,
			@RequestParam("headline") String headline, 
			@RequestParam("subheadline") String subheadline,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = image.getBytes();
			HeroBanner heroBanner = new HeroBanner();
			heroBanner.setHeadLine(headline);
			heroBanner.setSubHeadline(subheadline);

			HeroBanner savedEntity = heroBannerService.addSBanner(heroBanner, sessionId,
					fileBytes);

			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "subheadlne", required = false) String subheadlne,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (image != null) {
				fileBytes = image.getBytes();
			}
			HeroBanner heroBanner = new HeroBanner();
			heroBanner.setSubHeadline(subheadlne);
			heroBanner.setHeadLine(headline);
			HeroBanner updatedEntity = heroBannerService.updateBanner(id, heroBanner, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteBanner(@PathVariable int id) {
		heroBannerService.deleteBanner(id);
	}
}
