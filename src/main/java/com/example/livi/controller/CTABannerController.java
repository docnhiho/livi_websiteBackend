package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.CTABanner;
import com.example.livi.service.CTABannerService;
import com.example.livi.service.SessionService;

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
	public CTABanner addBanner(@RequestBody CTABanner ctaBanner, @PathVariable int sessionId) {
		return ctaBannerService.addBanner(ctaBanner, sessionId);
	}
	
	@PutMapping("/{id}")
	public CTABanner updateBanner(@PathVariable int id, @RequestBody CTABanner ctaBanner) {
		//TODO: process PUT request
		return ctaBannerService.updateBanner(id, ctaBanner);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBanner(@PathVariable int id) {
		ctaBannerService.deleteBanner(id);
	}

}
