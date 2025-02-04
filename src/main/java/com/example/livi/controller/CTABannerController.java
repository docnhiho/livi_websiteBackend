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
import com.example.livi.model.CTABanner;
import com.example.livi.service.CTABannerService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/ctabanner")
public class CTABannerController {

	@Autowired
	private CTABannerService ctaBannerService;

	@GetMapping("")
	public List<CTABanner> getAllBanner() {
		return ctaBannerService.getAllBanner();
	}

	@GetMapping("/{id}")
	public CTABanner getBannerById(@PathVariable int id) {
		return ctaBannerService.getBannerById(id);
	}

	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("coverImage") MultipartFile coverImage, 
			@RequestParam("lang") String lang,					
			@PathVariable int sessionId)
			throws IOException {
		try {
			byte[] fileBytes = coverImage.getBytes();
			CTABanner ctaBanner = new CTABanner();
			CTABanner savedEntity = ctaBannerService.addBanner(ctaBanner, sessionId, fileBytes);

			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
			@RequestParam(value = "lang", required = false) String lang,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (coverImage != null) {
				fileBytes = coverImage.getBytes();
			}
			CTABanner ctaBanner = new CTABanner();
			ctaBanner.setLang(lang);
			CTABanner updatedEntity = ctaBannerService.updateBanner(id, ctaBanner, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteBanner(@PathVariable int id) {
		ctaBannerService.deleteBanner(id);
	}

}
