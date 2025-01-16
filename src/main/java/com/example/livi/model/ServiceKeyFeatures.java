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
@Table(name = "ServiceKeyFeatures")
public class ServiceKeyFeatures {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Session session;
	
	@Column(name = "Thumbnail")
	private String thumbnail;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Link")
	private String link;
	
	@Column(name = "ButtonText")
	private String buttonText;

	public ServiceKeyFeatures(int id, Session session, String thumbnail, String name, String link, String buttonText) {
		super();
		this.id = id;
		this.session = session;
		this.thumbnail = thumbnail;
		this.name = name;
		this.link = link;
		this.buttonText = buttonText;
	}

	public ServiceKeyFeatures() {
		super();
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	
	
}
