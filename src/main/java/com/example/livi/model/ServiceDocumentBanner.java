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
@Table(name = "ServiceDocumentBanner")
public class ServiceDocumentBanner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Section session;

	@Column(name = "Langguage_Name")
	private String languageName;
	
	@Column(name = "Attachement")
	private String attachement;

	public ServiceDocumentBanner(int id, Section session, String languageName, String attachement) {
		super();
		this.id = id;
		this.session = session;
		this.languageName = languageName;
		this.attachement = attachement;
	}

	public ServiceDocumentBanner() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Section getSession() {
		return session;
	}

	public void setSession(Section session) {
		this.session = session;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}
	
	
}
