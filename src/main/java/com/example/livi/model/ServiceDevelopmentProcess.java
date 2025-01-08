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
@Table(name = "ServiceDevelopmentProcess")
public class ServiceDevelopmentProcess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Session_ID", nullable = false)
	private Session session;
	
	@Column(name = "Headline")
	private String Headline;
	
	@Column(name = "Subheadline")
	private String subHeadline;

	public ServiceDevelopmentProcess(int id, Session session, String headline, String subHeadline) {
		super();
		this.id = id;
		this.session = session;
		Headline = headline;
		this.subHeadline = subHeadline;
	}

	public ServiceDevelopmentProcess() {
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

	public String getHeadline() {
		return Headline;
	}

	public void setHeadline(String headline) {
		Headline = headline;
	}

	public String getSubHeadline() {
		return subHeadline;
	}

	public void setSubHeadline(String subHeadline) {
		this.subHeadline = subHeadline;
	}
	
	
}
