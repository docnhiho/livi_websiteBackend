package com.example.livi.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Technology")
public class TechListing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@Column(name = "Technology_Name")
	private String technologyName;

	@Column(name = "Link")
	private String link;
	
	@Column(name = "Meta_Description")
	private String metaDescription;

	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Created_Date")
	private Date createdDate;

	@Column(name = "Lang")
	private String lang;
	
	public TechListing() {
		super();
	}

	public TechListing(int id, Section session, String technologyName, String link, String metaDescription,
			String createdBy, Date createdDate, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.technologyName = technologyName;
		this.link = link;
		this.metaDescription = metaDescription;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
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

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
}
