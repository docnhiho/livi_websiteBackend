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

import com.example.livi.model.Contact;
import com.example.livi.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping("")
	public List<Contact> getAllSessions() {
		return contactService.getContacts();
	}
	
	@GetMapping("{id}")
	public Contact getSessionById(@PathVariable int id) {
		return contactService.getContactById(id);
	}
	
	@PostMapping("{sessionId}")
	public Contact addSession(@RequestBody Contact contact, @PathVariable int sessionId) {
		//TODO: process POST request
		return contactService.addContact(contact, sessionId);
	}
	
	@PutMapping("/{id}")
	public Contact updateSession(@PathVariable int id, @RequestBody Contact contact) {
		return contactService.updatContact(id, contact);
	}
	
	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable int id) {
		contactService.deleteContact(id);
	}
}
