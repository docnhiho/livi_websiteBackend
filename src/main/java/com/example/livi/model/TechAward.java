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
@Table(name = "TechAward")
public class TechAward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;

	@ManyToOne
	@JoinColumn(name = "Award_ID", nullable = false)
	private AboutAwardRecognition aboutAwardRecognition;
	
	@Column(name = "Thumbnail")
	private String thumbnail;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Lang")
	private String lang;



	public TechAward(int id, Section session, AboutAwardRecognition aboutAwardRecognition, String thumbnail,
			String name, String description, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.aboutAwardRecognition = aboutAwardRecognition;
		this.thumbnail = thumbnail;
		this.name = name;
		this.description = description;
		this.lang = lang;
	}

	public AboutAwardRecognition getAboutAwardRecognition() {
		return aboutAwardRecognition;
	}

	public void setAboutAwardRecognition(AboutAwardRecognition aboutAwardRecognition) {
		this.aboutAwardRecognition = aboutAwardRecognition;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public TechAward() {
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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
	
	
