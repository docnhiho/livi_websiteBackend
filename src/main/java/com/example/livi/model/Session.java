package com.example.livi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Session")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@Column(name = "Page_ID")
//	private int pageId;
	
	@ManyToOne// Lazy loading để không load dư thừa
	@JoinColumn(name = "Page_ID", nullable = false)
	private Page page;
	
	@Column(name = "Name")
	private String name;

	@Column(name = "Headline")
	private String headLine;

	@Column(name = "Subheadline")
	private String subHeadline;

	@Column(name = "Description")
	private String description;

	@Column(name = "Cover_Banner")
	private String coverBanner;

	public Session() {
		super();
	}

	public Session(int id, String name, String headLine, String subHeadline, String description, String coverBanner,
			Page page) {
		super();
		this.id = id;
		this.name = name;
		this.headLine = headLine;
		this.subHeadline = subHeadline;
		this.description = description;
		this.coverBanner = coverBanner;
		this.page = page;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverBanner() {
		return coverBanner;
	}

	public void setCoverBanner(String coverBanner) {
		this.coverBanner = coverBanner;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	
	
	
}
