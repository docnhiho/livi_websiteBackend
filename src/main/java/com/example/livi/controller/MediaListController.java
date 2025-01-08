package com.example.livi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.livi.model.MediaList;
import com.example.livi.service.MediaListService;

@RestController
@RequestMapping("/medialist")
public class MediaListController {

	@Autowired
	private MediaListService mediaListService;

	@GetMapping("")
	public List<MediaList> getAllSessions() {
		return mediaListService.getMediaLists();
	}
	
	@GetMapping("{id}")
	public MediaList getSessionById(@PathVariable int id) {
		return mediaListService.getMediaListById(id);
	}
	
	@PostMapping("{sessionId}")
	public MediaList addSession(@RequestBody MediaList mediaList, @PathVariable int sessionId) {
		//TODO: process POST request
		return mediaListService.addMediaList(mediaList, sessionId);
	}
	
	@PutMapping("/{id}")
	public MediaList updateSession(@PathVariable int id, @RequestBody MediaList mediaList) {
		return mediaListService.updateMediaList(id, mediaList);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMediaList(@PathVariable int id) {
		mediaListService.deleteMediaList(id);
	}
}