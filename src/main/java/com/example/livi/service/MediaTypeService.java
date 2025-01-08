package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.MediaType;
import com.example.livi.model.Session;
import com.example.livi.repository.MediaTypeRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class MediaTypeService {
	
	@Autowired
	private MediaTypeRepository mediaTypeRepository;
	@Autowired
	private SessionRepository sessionRepository;
	
	public List<MediaType> getMediaTypes(){
		return mediaTypeRepository.findAll();
	}
	
	public MediaType getMediaTypeById(int id) {
		return mediaTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public MediaType addMediaType(MediaType mediaType, int sessionId) {
		if (mediaType != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			mediaType.setSession(session);
			return mediaTypeRepository.save(mediaType);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public MediaType updateMediaType(int id, MediaType mediaType) {
		MediaType mediaType2 = getMediaTypeById(id);
		if(mediaType.getName() != null) {
			mediaType2.setName(mediaType.getName());
		}
		if(mediaType.getType() != null) {
			mediaType2.setType(mediaType.getType());
		}
		if(mediaType.getNote() != null) {
			mediaType2.setNote(mediaType.getNote());
		}
		return mediaTypeRepository.save(mediaType2);
	}
	
	public void deleteMediaType(int id) {
		if(!mediaTypeRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		mediaTypeRepository.deleteById(id);
	}
}

