package com.example.livi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HomelistClient")
public class HomelistClient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Session session;
	
	@ManyToOne
	@JoinColumn(name = "Client_ID", nullable = false)
	private ClientBrandList clientBrandList;

	public HomelistClient() {
		super();
	}

	public HomelistClient(int id, Session session, ClientBrandList clientBrandList) {
		super();
		this.id = id;
		this.session = session;
		this.clientBrandList = clientBrandList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ClientBrandList getClientBrandList() {
		return clientBrandList;
	}

	public void setClientBrandList(ClientBrandList clientBrandList) {
		this.clientBrandList = clientBrandList;
	}
	
	
}
