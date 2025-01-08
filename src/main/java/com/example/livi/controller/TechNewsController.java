package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public TechNews addTechNews(@RequestBody TechNews techNews, @PathVariable int sessionId) {
		//TODO: process POST request
		return techNewsService.addTechNews(techNews, sessionId);
	}
	
	@PutMapping("/{id}")
	public TechNews updatetTechNews(@PathVariable int id, @RequestBody TechNews techNews) {
		//TODO: process PUT request
		return techNewsService.updateTechNews(id, techNews);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTechNews(@PathVariable int id) {
		techNewsService.deleteTechNews(id);
	}
	
}
