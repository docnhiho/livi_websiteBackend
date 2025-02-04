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
@Table(name = "TechDevelopmentProcess")
public class TechDevelopmentProcess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Headline")
	private String headline;
	
	@Column(name = "Subheadline")
	private String subHeadline;
	
	@Column(name = "Lang")
	private String lang;

	public TechDevelopmentProcess(int id, Section session, String headline, String subHeadline, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.headline = headline;
		this.subHeadline = subHeadline;
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public TechDevelopmentProcess() {
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
	
	
}

