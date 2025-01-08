package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.ClientBrandList;
import com.example.livi.model.ClientBrandType;
import com.example.livi.model.HeroBanner;
import com.example.livi.model.Session;
import com.example.livi.repository.ClientBrandListRepository;
import com.example.livi.repository.HeroBannerRepository;
import com.example.livi.repository.SessionRepository;

@Service
public class ClientBrandListService {
	@Autowired
	private ClientBrandListRepository clientBrandListRepository;
	@Autowired
	private SessionRepository sessionRepository;

	public List<ClientBrandList> getBrandLists() {
		return clientBrandListRepository.findAll();
	}

	public ClientBrandList getBrandListById(int id) {
		return clientBrandListRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}

	public ClientBrandList addClientBrandList(ClientBrandList clientBrandList, int sessionId) {
		if (clientBrandList != null) {
			Session session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new RuntimeException("Page not found with id: " + sessionId));
			clientBrandList.setSession(session);
			return clientBrandListRepository.save(clientBrandList);
		}
		throw new IllegalArgumentException("Session cannot be null");
	}

	public ClientBrandList updateBrandList(int id, ClientBrandList clientBrandList) {
		ClientBrandList clientBrandList2 = getBrandListById(id);

		if (clientBrandList.getName() != null) {
			clientBrandList2.setName(clientBrandList.getName());
		}
		if (clientBrandList.getImgae() != null) {
			clientBrandList2.setImgae(clientBrandList.getImgae());
		}
		if (clientBrandList.getLink() != null) {
			clientBrandList2.setLink(clientBrandList.getLink());
		}
		if (clientBrandList.getClientBrandType() != null) {
			clientBrandList2.setClientBrandType(clientBrandList.getClientBrandType());
		}
		return clientBrandListRepository.save(clientBrandList2);
	}
	
	public void deleteClientBrandList(int id) {
		if(!clientBrandListRepository.existsById(id)) {
	        throw new RuntimeException("Banner not found with id: " + id);
		}
		clientBrandListRepository.deleteById(id);
	}
}
