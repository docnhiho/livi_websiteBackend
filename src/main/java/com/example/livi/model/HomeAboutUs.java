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
@Table(name = "HomeAboutUs")
public class HomeAboutUs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Headline")
	private String headline;
	
	@Column(name = "Subheadline")
	private String subHeadline;
	
	@Column(name = "ButtonLink")
	private String buttonLink;
	
	@Column(name = "Link")
	private String link;

	@Column(name = "Lang")
	private String lang;

	public HomeAboutUs() {
		super();
	}

	public HomeAboutUs(int id, Section session, String image, String headline, String subHeadline, String buttonLink,
			String link, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.image = image;
		this.headline = headline;
		this.subHeadline = subHeadline;
		this.buttonLink = buttonLink;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getSubHeadline() {
		return subHeadline;
	}

	public void setSubHeadline(String subHeadline) {
		this.subHeadline = subHeadline;
	}

	public String getButtonLink() {
		return buttonLink;
	}

	public void setButtonLink(String buttonLink) {
		this.buttonLink = buttonLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	
	
}
