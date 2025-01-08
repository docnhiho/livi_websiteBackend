package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.HeroBanner;
import com.example.livi.model.Session;
import com.example.livi.service.HeroBannerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/banner")
public class HeroBannerController {

	@Autowired
	private HeroBannerService heroBannerService;

	@GetMapping("")
	public List<HeroBanner> getAllBanners() {
		return heroBannerService.getAllBanner();
	}
	
	@GetMapping("{id}")
	public HeroBanner getSessionById(@PathVariable int id) {
		return heroBannerService.getBannerById(id);
	}
	
	@PostMapping("{sessionId}")
	public HeroBanner addBanner(@RequestBody HeroBanner banner, @PathVariable int sessionId) {
		//TODO: process POST request
		return heroBannerService.addSBanner(banner, sessionId);
	}
	@PutMapping("/{id}")
	public HeroBanner updateBanner(@PathVariable int id, @RequestBody HeroBanner banner) {
		return heroBannerService.updateBanner(id, banner);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBanner(@PathVariable int id) {
		heroBannerService.deleteBanner(id);
	}
}
