package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Contact;
import com.example.livi.model.HeroBanner;
import com.example.livi.model.Session;
import com.example.livi.repository.ContactRepository;
import com.example.livi.repository.HeroBannerRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private SessionRepository sessionRepository;



	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	public Contact getContactById(int id) {
		return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public Contact addContact(Contact contact, int sessionId) {
		if (contact != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			contact.setSession(session);
			return contactRepository.save(contact);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public Contact updatContact(int id, Contact contact) {
		Contact contact2 = getContactById(id);

		if (contact.getOffice() != null) {
			contact2.setOffice(contact.getOffice());
		}
		if (contact.getPhoneNumber() != null) {
			contact2.setPhoneNumber(contact.getPhoneNumber());
		}
		if (contact.getAddress() != null) {
			contact2.setAddress(contact.getAddress());
		}
		if (contact.getMapLink() != null) {
			contact2.setMapLink(contact.getMapLink());
		}
		return contactRepository.save(contact2);
	}

	public void deleteContact(int id) {
		if(!contactRepository.existsById(id)) {
	        throw new RuntimeException("Contact not found with id: " + id);
		}
		contactRepository.deleteById(id);
	}
}
