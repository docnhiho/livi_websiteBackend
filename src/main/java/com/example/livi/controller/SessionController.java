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
import com.example.livi.model.Session;
import com.example.livi.service.SessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	

	@GetMapping("")
	public List<Session> getAllSessions() {
		return sessionService.getAllSessions();
	}
	
	@GetMapping("{id}")
	public Session getSessionById(@PathVariable int id) {
		return sessionService.getSessionById(id);
	}
	
//	@PostMapping("{pageId}")
//	public Session addSession(@RequestBody Session session, @PathVariable int pageId) {
//		//TODO: process POST request
//		return sessionService.addSession(session, pageId);
//	}
	
	@PostMapping("/{pageId}")
	public ResponseEntity<?> add(@RequestParam(value = "Cover_Banner", required = false) MultipartFile converBanner,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "headline", required = false) String headline, 
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "subheadline", required = false) String subheadline,
			@RequestParam(value = "ButtonText", required = false) String buttonText,
			@RequestParam(value = "link", required = false) String link,
			@PathVariable int pageId) throws IOException {
		try {
			byte[] fileBytes = converBanner.getBytes();
			Session session = new Session();
			session.setHeadLine(headline);
			session.setDescription(description);
			session.setLink(link);
			session.setName(name);
			session.setSubHeadline(subheadline);
			session.setButtonText(buttonText);

			Session savedEntity = sessionService.addSession(session, pageId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "Cover_Banner", required = false) MultipartFile converBanner,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "headline", required = false) String headline, 
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "subheadline", required = false) String subheadline,
			@RequestParam(value = "ButtonText", required = false) String buttonText,
			@RequestParam(value = "link", required = false) String link,
			@PathVariable int id) throws IOException {
		try {
			byte[] fileBytes = null;
			if (converBanner != null) {
				fileBytes = converBanner.getBytes();
			}
			Session session = new Session();
			session.setDescription(description);
			session.setHeadLine(headline);
			session.setLink(link);
			session.setSubHeadline(subheadline);
			session.setButtonText(buttonText);
			session.setName(name);
			
			Session updatedEntity = sessionService.updateSession(id, session, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteSession(@PathVariable int id) {
		sessionService.deleteSession(id);
	}
}
