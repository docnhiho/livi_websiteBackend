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
@Table(name = "AboutHistory")
public class AboutHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Year")
	private String year;
	
	@Column(name = "Month")
	private String month;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Lang")
	private String Lang;
	
	public AboutHistory() {
		super();
	}

	public AboutHistory(int id, Section session, String year, String month, String description, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.year = year;
		this.month = month;
		this.description = description;
		Lang = lang;
	}

	public String getLang() {
		return Lang;
	}

	public void setLang(String lang) {
		Lang = lang;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
