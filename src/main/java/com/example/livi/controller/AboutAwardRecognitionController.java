package com.example.livi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.livi.model.AboutAwardRecognition;
import com.example.livi.model.AboutLiviLife;
import com.example.livi.service.AboutAwardRecognitionService;

@RestController
@RequestMapping("/aboutawardrecognition")
public class AboutAwardRecognitionController {

	@Autowired
	private AboutAwardRecognitionService aboutAwardRecognitionService;
	@Value("${file.upload-dir}")
	private String uploadDir;

	@GetMapping("")
	public List<AboutAwardRecognition> getAboutAwardRecognitions() {
		return aboutAwardRecognitionService.getAboutAwardRecognitions();
	}

	@GetMapping("{id}")
	public AboutAwardRecognition getAboutAwardRecognitionByAboutAwardRecognitionId(@PathVariable int id) {
		return aboutAwardRecognitionService.getAboutAwardRecognitionById(id);
	}


	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@PathVariable int sessionId, @RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("description") String description) throws IOException {
		try {
			byte[] fileBytes = file.getBytes();
			AboutAwardRecognition aboutAwardRecognition = new AboutAwardRecognition();
			aboutAwardRecognition.setName(name);
			aboutAwardRecognition.setDescription(description);

			AboutAwardRecognition savedEntity = aboutAwardRecognitionService
					.addAboutAwardRecognition(aboutAwardRecognition, sessionId, fileBytes);

			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		try {
			byte[] fileBytes = null;
			if (file != null) {
				fileBytes = file.getBytes();
			}
			AboutAwardRecognition aboutAwardRecognition = new AboutAwardRecognition();
			aboutAwardRecognition.setName(name);
			aboutAwardRecognition.setDescription(description);
			AboutAwardRecognition updatedEntity = aboutAwardRecognitionService.updateAboutAwardRecognition(id,
					aboutAwardRecognition, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteAboutAwardRecognition(@PathVariable int id) {
		aboutAwardRecognitionService.deleteAboutAwardRecognition(id);
	}
}
