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

import com.example.livi.model.MediaType;
import com.example.livi.model.Section;
import com.example.livi.service.MediaTypeService;

@RestController
@RequestMapping("/mediatype")
public class MediaTypeController {
	@Autowired
	private MediaTypeService mediaTypeService;
	
	@GetMapping("")
	public List<MediaType> getAllSessions() {
		return mediaTypeService.getMediaTypes();
	}
	
	@GetMapping("{id}")
	public MediaType geMediaTypeById(@PathVariable int id) {
		return mediaTypeService.getMediaTypeById(id);
	}
	
	@PostMapping("{pageId}")
	public MediaType addMediaType(@RequestBody MediaType mediaType, @PathVariable int pageId) {
		//TODO: process POST request
		return mediaTypeService.addMediaType(mediaType, pageId);
	}
	
	@PutMapping("/{id}")
	public MediaType updaMediaType(@PathVariable int id, @RequestBody MediaType mediaType) {
		return mediaTypeService.updateMediaType(id, mediaType);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMediaType(@PathVariable int id) {
		mediaTypeService.deleteMediaType(id);
	}
}
