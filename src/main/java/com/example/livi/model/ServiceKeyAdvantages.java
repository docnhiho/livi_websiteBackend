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
@Table(name = "ServiceKeyAdvantages")
public class ServiceKeyAdvantages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Headline")
	private String headLine;
	
	@Column(name = "Description")
	private String description;

	@Column(name = "Lang")
	private String lang;

	public ServiceKeyAdvantages(int id, Section session, String headLine, String description, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.headLine = headLine;
		this.description = description;
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public ServiceKeyAdvantages() {
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

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
