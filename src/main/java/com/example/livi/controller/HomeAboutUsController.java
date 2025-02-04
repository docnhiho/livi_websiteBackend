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
import com.example.livi.model.HomeAboutUs;
import com.example.livi.service.HomeAboutUsService;

@RestController
@RequestMapping("/homeaboutus")
public class HomeAboutUsController {

	@Autowired
	private HomeAboutUsService homeAboutUsService;
	
	@GetMapping("")
	public List<HomeAboutUs> getAllSessions() {
		return homeAboutUsService.getHomeAboutUs();
	}
	
	@GetMapping("/{id}")
	public HomeAboutUs getHomeAboutUsById(@PathVariable int id) {
		return homeAboutUsService.getHomeAboutUsById(id);
	}
	
	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("image") MultipartFile image,
			@RequestParam("headline") String headline, 
			@RequestParam("subheadline") String subheadline,
			@RequestParam("ButtonLink") String buttonLink,
			@RequestParam("Link") String link,
			@RequestParam("Lang") String lang,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = image.getBytes();
			HomeAboutUs homeAboutUs = new HomeAboutUs();
			homeAboutUs.setHeadline(headline);
			homeAboutUs.setSubHeadline(subheadline);
			homeAboutUs.setButtonLink(buttonLink);
			homeAboutUs.setLang(lang);
			homeAboutUs.setLink(link);
			HomeAboutUs savedEntity = homeAboutUsService.addHomeAboutUs(homeAboutUs, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "subheadline", required = false) String subheadline,
			@RequestParam(value = "ButtonLink", required = false) String ButtonLink,
			@RequestParam(value = "Lang", required = false) String lang,
			@RequestParam(value = "Link", required = false) String link,

			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (image != null) {
				fileBytes = image.getBytes();
			}
			HomeAboutUs homeAboutUs = new HomeAboutUs();
			homeAboutUs.setSubHeadline(subheadline);
			homeAboutUs.setHeadline(headline);
			homeAboutUs.setButtonLink(ButtonLink);
			homeAboutUs.setLink(link);
			homeAboutUs.setLang(lang);
			HomeAboutUs updatedEntity = homeAboutUsService.updateHomeAboutUs(id, homeAboutUs, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		homeAboutUsService.deleteHomeAboutUs(id);
	}
}
