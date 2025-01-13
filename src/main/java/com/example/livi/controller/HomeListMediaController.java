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
import com.example.livi.model.HomeListMedia;
import com.example.livi.service.HomeListMediaService;

@RestController
@RequestMapping("/homelistmedia")
public class HomeListMediaController {

	@Autowired
	private HomeListMediaService homeListMediaService;

	@GetMapping("")
	public List<HomeListMedia> getAllSessions() {
		return homeListMediaService.getHomeListMedias();
	}

	@GetMapping("{id}")
	public HomeListMedia getSessionById(@PathVariable int id) {
		return homeListMediaService.getHomeListMedia(id);
	}

	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("image") MultipartFile image,
			@RequestParam("description") String description,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = image.getBytes();
			HomeListMedia homeListMedia = new HomeListMedia();
			homeListMedia.setDescription(description);

			HomeListMedia savedEntity = homeListMediaService.addHomeListMedia(homeListMedia, sessionId, fileBytes);

			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "description", required = false) String description, @PathVariable int id)
			throws IOException {
		try {
			byte[] fileBytes = null;
			if (image != null) {
				fileBytes = image.getBytes();
			}
			HomeListMedia homeListMedia = new HomeListMedia();
			homeListMedia.setDescription(description);

			HomeListMedia updatedEntity = homeListMediaService.updateHomeListMedia(id, homeListMedia, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		homeListMediaService.deleteHomeListMedia(id);
	}
}
