package com.example.livi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Session")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Page_ID", nullable = false)
	private Page page;
	
	@OneToMany(mappedBy = "session")
	@JsonIgnore
	public List<HeroBanner> heroBanners;
	
	@OneToMany(mappedBy = "session")
	@JsonIgnore
	public List<Introduction> introduction;
	
	@OneToMany(mappedBy = "session")
	@JsonIgnore
	public List<CTABanner> ctaBanner;
	
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

	@Column(name = "ButtonText")
	private String buttonText;
	
	@Column(name = "Link")
	private String link;
	
	public Session() {
		super();
	}

	public Session(int id, Page page, List<HeroBanner> heroBanners, List<Introduction> introduction,
			List<CTABanner> ctaBanner, String name, String headLine, String subHeadline, String description,
			String coverBanner, String buttonText, String link) {
		super();
		this.id = id;
		this.page = page;
		this.heroBanners = heroBanners;
		this.introduction = introduction;
		this.ctaBanner = ctaBanner;
		this.name = name;
		this.headLine = headLine;
		this.subHeadline = subHeadline;
		this.description = description;
		this.coverBanner = coverBanner;
		this.buttonText = buttonText;
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<HeroBanner> getHeroBanners() {
		return heroBanners;
	}

	public void setHeroBanners(List<HeroBanner> heroBanners) {
		this.heroBanners = heroBanners;
	}

	public List<Introduction> getIntroduction() {
		return introduction;
	}

	public void setIntroduction(List<Introduction> introduction) {
		this.introduction = introduction;
	}

	public List<CTABanner> getCtaBanner() {
		return ctaBanner;
	}

	public void setCtaBanner(List<CTABanner> ctaBanner) {
		this.ctaBanner = ctaBanner;
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
