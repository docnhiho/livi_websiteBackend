package com.example.livi.service;

import java.util.List;

import com.example.livi.model.Session;

public interface SessionInterface {
	
	public List<Session> getAllSessions();
	public Session addSession(Session session, int pageId);
	public Session getSessionById(int id);
	public Session updateSession(int id, Session session);
	public void deleteSession(int id);
	
	
	
}
