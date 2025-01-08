package com.example.livi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HomeListService")
public class HomeListService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Session session;
	
	@ManyToOne
	@JoinColumn(name = "Service_List_ID", nullable = false)
	private ServiceListing serviceListing;
	
	@ManyToOne
	@JoinColumn(name = "Hero_Banner_ID", nullable = false)
	private HeroBanner heroBanner;
	
	@ManyToOne
	@JoinColumn(name = "Related_New_ID", nullable = false)
	private TechNews techNews;

	public HomeListService() {
		super();
	}

	public HomeListService(int id, Session session, ServiceListing serviceListing, HeroBanner heroBanner,
			TechNews techNews) {
		super();
		this.id = id;
		this.session = session;
		this.serviceListing = serviceListing;
		this.heroBanner = heroBanner;
		this.techNews = techNews;
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

	public ServiceListing getServiceListing() {
		return serviceListing;
	}

	public void setServiceListing(ServiceListing serviceListing) {
		this.serviceListing = serviceListing;
	}

	public HeroBanner getHeroBanner() {
		return heroBanner;
	}

	public void setHeroBanner(HeroBanner heroBanner) {
		this.heroBanner = heroBanner;
	}

	public TechNews getTechNews() {
		return techNews;
	}

	public void setTechNews(TechNews techNews) {
		this.techNews = techNews;
	}
	
	
}
