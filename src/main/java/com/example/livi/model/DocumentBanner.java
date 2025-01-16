package com.example.livi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DocumentBanner")
public class DocumentBanner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Session session;
	
	@Column(name = "Attachement")
	private String attachement;
	
	@Column(name = "Path")
	private String path;
	
	@Column(name = "Language")
	private String language;


	

	public DocumentBanner() {
		super();
	}



	public DocumentBanner(int id, Session session, String attachement, String path, String language) {
		super();
		this.id = id;
		this.session = session;
		this.attachement = attachement;
		this.path = path;
		this.language = language;
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



	public String getAttachement() {
		return attachement;
	}



	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}

	
	
}

