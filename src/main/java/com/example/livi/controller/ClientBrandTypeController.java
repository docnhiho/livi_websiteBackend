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

import com.example.livi.model.ClientBrandType;
import com.example.livi.model.Section;
import com.example.livi.service.ClientBrandTypeService;

@RestController
@RequestMapping("/clientbrandtype")
public class ClientBrandTypeController {
	@Autowired
	private ClientBrandTypeService clientBrandTypeService;
	
	@GetMapping("")
	public List<ClientBrandType> geBrandTypes() {
		return clientBrandTypeService.geBrandTypes();
	}
	
	@GetMapping("{id}")
	public ClientBrandType geClientBrandType(@PathVariable int id) {
		return clientBrandTypeService.getcBrandTypeById(id);
	}
	
	@PostMapping("{sessionId}")
	public ClientBrandType addBrandType(@RequestBody ClientBrandType clientBrandType, @PathVariable int sessionId) {
		//TODO: process POST request
		return clientBrandTypeService.addClientBrandType(clientBrandType, sessionId);
	}
	
	@PutMapping("/{id}")
	public ClientBrandType updateBrandType(@PathVariable int id, @RequestBody ClientBrandType clientBrandType) {
		return clientBrandTypeService.updaBrandType(id, clientBrandType);
	}
	
	@DeleteMapping("/{id}")
	public void deleteClientBrandType(@PathVariable int id) {
		clientBrandTypeService.deleteClientBrandType(id);
	}
}
