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
import com.example.livi.model.ServiceMoreInfo;
import com.example.livi.service.ServiceMoreInfoSerivce;

@RestController
@RequestMapping("/servicemoreinfo")
public class ServiceMoreInfoController {

	@Autowired
	private ServiceMoreInfoSerivce serviceMoreInfoSerivce;

	@GetMapping("")
	public List<ServiceMoreInfo> geServiceMoreInfos() {
		return serviceMoreInfoSerivce.getServiceMoreInfo();
	}

	@GetMapping("{id}")
	public ServiceMoreInfo getServiceMoreInfoById(@PathVariable int id) {
		return serviceMoreInfoSerivce.getServiceMoreInfoById(id);
	}

	@PostMapping("/{sessionId}")
	public ResponseEntity<?> add(@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("headline") String headline, @RequestParam("subheadline") String subheadline,
			@PathVariable int sessionId) throws IOException {
		try {
			byte[] fileBytes = thumbnail.getBytes();
			ServiceMoreInfo serviceMoreInfo = new ServiceMoreInfo();
			serviceMoreInfo.setHeadline(headline);
			serviceMoreInfo.setSubHeadline(subheadline);

			ServiceMoreInfo savedEntity = serviceMoreInfoSerivce.addServiceMoreInfo(serviceMoreInfo, sessionId,
					fileBytes);
			return ResponseEntity.ok(savedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
			@RequestParam(value = "headline", required = false) String headline,
			@RequestParam(value = "subheadline", required = false) String subheadline, @PathVariable int id)
			throws IOException {
		try {
			byte[] fileBytes = null;
			if (thumbnail != null) {
				fileBytes = thumbnail.getBytes();
			}
			ServiceMoreInfo serviceMoreInfo = new ServiceMoreInfo();
			serviceMoreInfo.setHeadline(headline);
			serviceMoreInfo.setSubHeadline(subheadline);
			ServiceMoreInfo updatedEntity = serviceMoreInfoSerivce.updateServiceMoreInfo(id, serviceMoreInfo, fileBytes);

			return ResponseEntity.ok(updatedEntity);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteServiceMoreInfo(@PathVariable int id) {
		serviceMoreInfoSerivce.deleteServiceMoreInfo(id);
	}
}
