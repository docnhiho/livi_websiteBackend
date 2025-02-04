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
@Table(name = "herobanner")
public class HeroBanner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Headline")
	private String headLine;
	
	@Column(name = "Subheadline")
	private String subHeadline;

	@Column(name = "ButtonText")
	private String buttonText;
	
	@Column(name = "Link")
	private String link;
	@Column(name = "Lang")
	private String lang;
	

	public HeroBanner(int id, Section session, String image, String headLine, String subHeadline, String buttonText,
			String link, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.image = image;
		this.headLine = headLine;
		this.subHeadline = subHeadline;
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

	public HeroBanner() {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public String getSubHeadline() {
		return subHeadline;
	}

	public void setSubHeadline(String subHeadline) {
		this.subHeadline = subHeadline;
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
