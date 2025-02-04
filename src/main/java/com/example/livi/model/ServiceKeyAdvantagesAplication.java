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
@Table(name = "ServiceKeyAdvantagesAplicationList")
public class ServiceKeyAdvantagesAplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Thumbnail")
	private String thumbnail;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Link")
	private String link;

	@Column(name = "Lang")
	private String lang;
	public ServiceKeyAdvantagesAplication() {
		super();
	}

	public ServiceKeyAdvantagesAplication(int id, Section session, String thumbnail, String name, String link,
			String lang) {
		super();
		this.id = id;
		this.session = session;
		this.thumbnail = thumbnail;
		this.name = name;
		this.link = link;
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
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
	
	
}
