package com.example.livi.service;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.livi.model.Page;
import com.example.livi.model.Section;
import com.example.livi.repository.PageRepository;
import com.example.livi.repository.SectionRepository;

@Service
public class SectionService  {

	@Autowired
	private SectionRepository sessionRepository;
	@Autowired
	private PageRepository pageRepository;

	public SectionService(SectionRepository sessionRepository, PageRepository pageRepository) {
		this.sessionRepository = sessionRepository;
		this.pageRepository = pageRepository;
	}

	public List<Section> getAllSessions() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll();
	}

//	public Session addSession(Session session, int pageId) {
//		if (session != null) {
//			Page page = pageRepository.findById(pageId)
//					.orElseThrow(() -> new RuntimeException("Page not found with id: " + pageId));
//			session.setPage(page);
//			return sessionRepository.save(session);
//		}
//		throw new IllegalArgumentException("Session cannot be null");
//	}
	
	public Section addSession(Section session, int pageId, byte[] fileBytes) {
		String base64Image = Base64.getEncoder().encodeToString(fileBytes);

		Page page = pageRepository.findById(pageId)
				.orElseThrow(() -> new IllegalArgumentException("Session không tồn tại!"));
		session.setPage(page);
		session.setCoverBanner(base64Image);

		return sessionRepository.save(session);
	}
	

	public Section getSessionById(int id) {
		// TODO Auto-generated method stub
		return sessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public Section updateSession(int id, Section session, byte[] fileBytes) {
		String base64Image = fileBytes != null && fileBytes.length > 0 ? Base64.getEncoder().encodeToString(fileBytes)
				: null;
		Optional<Section> optional = sessionRepository.findById(id);
		if (optional.isPresent()) {
			Section existingEntity = optional.get();
			if (session.getHeadLine() != null) {
				existingEntity.setHeadLine(session.getHeadLine());
			}
			if (session.getName() != null) {
				existingEntity.setName(session.getName());
			}
			if (session.getSubHeadline() != null) {
				existingEntity.setSubHeadline(session.getSubHeadline());
			}
			if (session.getDescription() != null) {
				existingEntity.setDescription(session.getDescription());
			}
			if (session.getButtonText() != null) {
				existingEntity.setButtonText(session.getButtonText());
			}
			if (session.getLink() != null) {
				existingEntity.setLink(session.getLink());
			}
			if (session.getLang() != null) {
				existingEntity.setLang(session.getLang());
			}
			if (base64Image != null && !base64Image.equals(existingEntity.getCoverBanner())) {
				existingEntity.setCoverBanner(base64Image);
			}
			return sessionRepository.save(existingEntity);
		} else {
			throw new IllegalArgumentException("AboutAwardRecognition not found with ID " + id);
		}
	}

	public void deleteSession(int id) {
		// TODO Auto-generated method stub
		sessionRepository.deleteById(id);

	}

}
