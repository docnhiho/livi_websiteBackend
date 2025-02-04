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
@Table(name = "TechKeyCapability")
public class TechKeyCapability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Headline")
	private String headline;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Thumbnail")
	private String thumbnail;
	
	@Column(name = "Button_Text")
	private String buttonText;

	@Column(name = "Link")
	private String link;
	
	@Column(name = "Lang")
	private String lang;

	public TechKeyCapability() {
		super();
	}

	public TechKeyCapability(int id, Section session, String headline, String description, String thumbnail,
			String buttonText, String link, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.headline = headline;
		this.description = description;
		this.thumbnail = thumbnail;
		this.buttonText = buttonText;
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

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
