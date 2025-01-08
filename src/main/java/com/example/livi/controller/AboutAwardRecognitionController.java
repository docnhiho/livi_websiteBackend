package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.AboutAwardRecognition;
import com.example.livi.service.AboutAwardRecognitionService;

@RestController
@RequestMapping("/aboutawardrecognition")
public class AboutAwardRecognitionController {

	@Autowired
	private AboutAwardRecognitionService aboutAwardRecognitionService;
	
	@GetMapping("")
	public List<AboutAwardRecognition> getAllSessions() {
		return aboutAwardRecognitionService.getAboutAwardRecognitions();
	}
	
	@GetMapping("{id}")
	public AboutAwardRecognition getSessionById(@PathVariable int id) {
		return aboutAwardRecognitionService.getAboutAwardRecognitionById(id);
	}
	
	@PostMapping("{sessionId}")
	public AboutAwardRecognition addSession(@RequestBody AboutAwardRecognition aboutAwardRecognition, @PathVariable int sessionId) {
		//TODO: process POST request
		return aboutAwardRecognitionService.addAboutAwardRecognition(aboutAwardRecognition, sessionId);
	}
	
	@PutMapping("/{id}")
	public AboutAwardRecognition updateSession(@PathVariable int id, @RequestBody AboutAwardRecognition aboutAwardRecognition) {
		return aboutAwardRecognitionService.updateAboutAwardRecognition(id, aboutAwardRecognition);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAboutAwardRecognition(@PathVariable int id) {
		aboutAwardRecognitionService.deleteAboutAwardRecognition(id);
	}
}
