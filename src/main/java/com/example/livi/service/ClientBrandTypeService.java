package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ClientBrandType;

import com.example.livi.model.Session;
import com.example.livi.repository.ClientBrandTypeRepository;

import com.example.livi.repository.SessionRepository;

@Service
public class ClientBrandTypeService {
	@Autowired
	private ClientBrandTypeRepository clientBrandTypeRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<ClientBrandType> geBrandTypes() {
		return clientBrandTypeRepository.findAll();
	}

	public ClientBrandType getcBrandTypeById(int id) {
		return clientBrandTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public ClientBrandType addClientBrandType(ClientBrandType clientBrandType, int sessionId) {
		if (clientBrandType != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			clientBrandType.setSession(session);
			return clientBrandTypeRepository.save(clientBrandType);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public ClientBrandType updaBrandType(int id, ClientBrandType clientBrandType) {
		ClientBrandType clientBrandType2 = getcBrandTypeById(id);

		if (clientBrandType.getName() != null) {
			clientBrandType2.setName(clientBrandType.getName());
		}
		if (clientBrandType.getType() != null) {
			clientBrandType2.setType(clientBrandType.getType());
		}
		if (clientBrandType.getNote() != null) {
			clientBrandType2.setNote(clientBrandType.getNote());
		}
		return clientBrandTypeRepository.save(clientBrandType2);
	}

	public void deleteClientBrandType(int id) {
		if (!clientBrandTypeRepository.existsById(id)) {
			throw new RuntimeException("Type not found with id: " + id);
		}
		clientBrandTypeRepository.deleteById(id);
	}
}
