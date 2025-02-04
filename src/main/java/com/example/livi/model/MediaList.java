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
@Table(name = "MediaList")
public class MediaList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@ManyToOne
	@JoinColumn(name = "Type_ID", nullable = false)
	private MediaType mediaType;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Link")
	private String link;
	
	@Column(name = "SuggestSession")
	private String suggestSession;
	
	@Column(name = "Lang")
	private String lang;

	

	public MediaList(int id, Section session, MediaType mediaType, String title, String link, String suggestSession,
			String lang) {
		super();
		this.id = id;
		this.session = session;
		this.mediaType = mediaType;
		this.title = title;
		this.link = link;
		this.suggestSession = suggestSession;
		this.lang = lang;
	}

	public MediaList() {
		super();
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

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSuggestSession() {
		return suggestSession;
	}

	public void setSuggestSession(String suggestSession) {
		this.suggestSession = suggestSession;
	}
	
	

}
