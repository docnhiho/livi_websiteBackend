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

import com.example.livi.model.ClientBrandList;
import com.example.livi.service.ClientBrandListService;

@RestController
@RequestMapping("/clientbrandlist")
public class ClientBrandListController {

	@Autowired
	private ClientBrandListService clientBrandListService;
	
	@GetMapping("")
	public List<ClientBrandList> getAllSessions() {
		return clientBrandListService.getBrandLists();
	}
	
	@GetMapping("{id}")
	public ClientBrandList getSessionById(@PathVariable int id) {
		return clientBrandListService.getBrandListById(id);
	}
	
	@PostMapping("{sessionId}")
	public ClientBrandList addSession(@RequestBody ClientBrandList clientBrandList, @PathVariable int sessionId) {
		//TODO: process POST request
		return clientBrandListService.addClientBrandList(clientBrandList, sessionId);
	}
	
	@PutMapping("/{id}")
	public ClientBrandList updateSession(@PathVariable int id, @RequestBody ClientBrandList clientBrandList) {
		return clientBrandListService.updateBrandList(id, clientBrandList);
	}
	
	@DeleteMapping("/{id}")
	public void deleteClientBrandList(@PathVariable int id) {
		clientBrandListService.deleteClientBrandList(id);
	}
}
