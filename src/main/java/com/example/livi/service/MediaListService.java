package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.MediaList;
import com.example.livi.model.Section;
import com.example.livi.repository.MediaListRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class MediaListService {

	@Autowired
	private MediaListRepository mediaListRepository;
	@Autowired
	private SectionRepository sessionRepository;

	public List<MediaList> getMediaLists() {
		return mediaListRepository.findAll();
	}

	public MediaList getMediaListById(int id) {
		return mediaListRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	public MediaList addMediaList(MediaList mediaList, int sessionId) {
		if (mediaList != null) {
			Section session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			mediaList.setSession(session);
			return mediaListRepository.save(mediaList);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}
	
	public MediaList updateMediaList(int id, MediaList mediaList) {
		MediaList mediaList2 = getMediaListById(id);
		  if (mediaList.getTitle() != null) {
			  mediaList2.setTitle(mediaList.getTitle());
	        }
	        if (mediaList.getLink() != null) {
	        	mediaList2.setLink(mediaList.getLink());
	        }
	        if (mediaList.getMediaType() != null) {
	        	mediaList2.setMediaType(mediaList.getMediaType());
	        }
	        if (mediaList.getLang() != null) {
	        	mediaList2.setLang(mediaList.getLang());
	        }
		
		return mediaListRepository.save(mediaList2);
	}
	
	public void deleteMediaList(int id) {
		if(!mediaListRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		mediaListRepository.deleteById(id);
	}
}
