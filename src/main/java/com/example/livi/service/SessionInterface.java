package com.example.livi.service;

import java.util.List;

import com.example.livi.model.Section;

public interface SessionInterface {
	
	public List<Section> getAllSessions();
	public Section addSession(Section session, int pageId);
	public Section getSessionById(int id);
	public Section updateSession(int id, Section session);
	public void deleteSession(int id);
	
	
	
}
