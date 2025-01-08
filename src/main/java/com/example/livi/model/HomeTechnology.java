package com.example.livi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HomeTechnology")
public class HomeTechnology {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Session session;
	
	@ManyToOne
	@JoinColumn(name = "Tech_Listing_ID", nullable = false)
	private TechListing techListing;
	
	@ManyToOne
	@JoinColumn(name = "Related_New_ID", nullable = false)
	private TechNews techNews;
	
	@ManyToOne
	@JoinColumn(name = "Award_ID", nullable = false)
	private TechAward techAward;

	public HomeTechnology(int id, Session session, TechListing techListing, TechNews techNews, TechAward techAward) {
		super();
		this.id = id;
		this.session = session;
		this.techListing = techListing;
		this.techNews = techNews;
		this.techAward = techAward;
	}

	public HomeTechnology() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public TechListing getTechListing() {
		return techListing;
	}

	public void setTechListing(TechListing techListing) {
		this.techListing = techListing;
	}

	public TechNews getTechNews() {
		return techNews;
	}

	public void setTechNews(TechNews techNews) {
		this.techNews = techNews;
	}

	public TechAward getTechAward() {
		return techAward;
	}

	public void setTechAward(TechAward techAward) {
		this.techAward = techAward;
	}
	
	

}
