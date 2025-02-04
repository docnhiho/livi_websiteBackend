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
@Table(name = "TechNews")
public class TechNews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private MediaList mediaList;
	
	@Column(name = "Thumbnail")
	private String thumbnail;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String Description;
	
	@Column(name = "Lang")
	private String lang;

	public TechNews(int id, Section session, MediaList mediaList, String thumbnail, String name, String description,
			String lang) {
		super();
		this.id = id;
		this.session = session;
		this.mediaList = mediaList;
		this.thumbnail = thumbnail;
		this.name = name;
		Description = description;
		this.lang = lang;
	}

	public MediaList getMediaList() {
		return mediaList;
	}

	public void setMediaList(MediaList mediaList) {
		this.mediaList = mediaList;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public TechNews() {
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
}
