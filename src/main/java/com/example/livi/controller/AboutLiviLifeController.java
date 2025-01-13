package com.example.livi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

import com.example.livi.model.AboutAwardRecognition;
import com.example.livi.model.AboutLiviLife;
import com.example.livi.model.CEOSetting;
import com.example.livi.service.AboutLiviLifeService;

@RestController
@RequestMapping("/aboutlivilife")
public class AboutLiviLifeController {

	@Autowired
	private AboutLiviLifeService aboutLiviLifeService;

	@Value("${file.upload-dir}")
	private String uploadDir;

	@GetMapping("")
	public List<AboutLiviLife> getAllSessions() {
		return aboutLiviLifeService.getAboutLiviLifes();
	}

	@GetMapping("/{id}")
	public AboutLiviLife getAllSessionsById(@PathVariable int id) {
		return aboutLiviLifeService.getAboutLiviLifeById(id);
	}

	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("image") MultipartFile image,
			@RequestParam("headline") String headline, @RequestParam("description") String description,
			@RequestParam("link") String link, @PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = image.getBytes();
			AboutLiviLife aboutAwardRecognition = new AboutLiviLife();
			aboutAwardRecognition.setHeadLine(headline);
			aboutAwardRecognition.setDescription(description);
			aboutAwardRecognition.setLink(link);
			AboutLiviLife savedEntity = aboutLiviLifeService.addAboutLiviLife(aboutAwardRecognition, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "link", required = false) String link, @PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (image != null) {
				fileBytes = image.getBytes();
			}
			AboutLiviLife aboutLiviLife = new AboutLiviLife();
			aboutLiviLife.setDescription(description);
			aboutLiviLife.setHeadLine(headline);
			aboutLiviLife.setLink(link);
			AboutLiviLife updatedEntity = aboutLiviLifeService.updateAboutLiviLife(id, aboutLiviLife, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		aboutLiviLifeService.deleteAboutLiviLife(id);
	}
}
