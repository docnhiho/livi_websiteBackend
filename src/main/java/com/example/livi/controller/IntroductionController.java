package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.Introduction;
import com.example.livi.service.IntroductionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/introduction")
public class IntroductionController {

	@Autowired
	private IntroductionService introductionService;

	@GetMapping("")
	public List<Introduction> getAllIntroductions(){
		return introductionService.getAllIntroductions();
	}
	
	@GetMapping("/{id}")
	public Introduction getIntroductionById(@PathVariable int id){
		return introductionService.getIntroductionById(id);
	}
	
	@PostMapping("{sessionId}")
	public Introduction addIntroduction(@RequestBody Introduction introduction, @PathVariable int sessionId) {
		//TODO: process POST request
		
		return introductionService.addIntroduction(introduction, sessionId);
	}
	
	@PutMapping("/{id}")
	public Introduction putMethodName(@PathVariable int id, @RequestBody Introduction introduction) {
		//TODO: process PUT request
		
		return introductionService.updaIntroduction(id, introduction);
	}
	
	@DeleteMapping("/{id}")
	public void deleteIntroduction(@PathVariable int id) {
		introductionService.deleteIntroduction(id);
	}
	
}
