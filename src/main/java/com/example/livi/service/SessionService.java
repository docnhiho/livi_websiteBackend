package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Page;
import com.example.livi.model.Session;
import com.example.livi.repository.PageRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class SessionService implements SessionInterface {

	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private PageRepository pageRepository;

	public SessionService(SessionRepository sessionRepository, PageRepository pageRepository) {
		this.sessionRepository = sessionRepository;
		this.pageRepository = pageRepository;
	}

	@Override
	public List<Session> getAllSessions() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll();
	}

	@Override
//	public Session addSession(Session session) {
//		// TODO Auto-generated method stub
//		if(session !=null) {
//			return sessionRepository.save(session);
//		}
//		return null;
//	}

	public Session addSession(Session session, int pageId) {
		if (session != null) {
			Page page = pageRepository.findById(pageId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + pageId));
			session.setPage(page);
			return sessionRepository.save(session);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	@Override
	public Session getSessionById(int id) {
		// TODO Auto-generated method stub
		return sessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	@Override
	public Session updateSession(int id, Session session) {
		Session existingSession = getSessionById(id);

		if (session.getName() != null) {
			existingSession.setName(session.getName());
		}
		if (session.getHeadLine() != null) {
			existingSession.setHeadLine(session.getHeadLine());
		}
		if (session.getSubHeadline() != null) {
			existingSession.setSubHeadline(session.getSubHeadline());
		}
		if (session.getDescription() != null) {
			existingSession.setDescription(session.getDescription());
		}
		if (session.getCoverBanner() != null) {
			existingSession.setCoverBanner(session.getCoverBanner());
		}
		return sessionRepository.save(existingSession);
	}

	@Override
	public void deleteSession(int id) {
		// TODO Auto-generated method stub
		sessionRepository.deleteById(id);

	}

}
