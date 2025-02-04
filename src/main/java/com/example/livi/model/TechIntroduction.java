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
@Table(name = "TechIntroduction")
public class TechIntroduction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Icon_Display")
	private String icon;
	
	@Column(name = "Headline")
	private String headline;
	
	@Column(name = "Description")
	private String description;

	
	@Column(name = "Lang")
	private String lang;

	public TechIntroduction(int id, Section session, String icon, String headline, String description, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.icon = icon;
		this.headline = headline;
		this.description = description;
		this.lang = lang;
	}


	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	public TechIntroduction() {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
	
	
}
