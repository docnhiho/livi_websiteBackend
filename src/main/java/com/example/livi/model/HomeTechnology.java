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
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@ManyToOne
	@JoinColumn(name = "Tech_Listing_ID", nullable = false)
	private TechListing techListing;
	
	@ManyToOne
	@JoinColumn(name = "Related_New_ID", nullable = false)
	private MediaList mediaList;
	
	@ManyToOne
	@JoinColumn(name = "Award_ID", nullable = false)
	private AboutAwardRecognition AboutAwardRecognition;





	public HomeTechnology(int id, Section session, TechListing techListing, MediaList mediaList,
			com.example.livi.model.AboutAwardRecognition aboutAwardRecognition) {
		super();
		this.id = id;
		this.session = session;
		this.techListing = techListing;
		this.mediaList = mediaList;
		AboutAwardRecognition = aboutAwardRecognition;
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

	public Section getSession() {
		return session;
	}

	public void setSession(Section session) {
		this.session = session;
	}

	public TechListing getTechListing() {
		return techListing;
	}

	public void setTechListing(TechListing techListing) {
		this.techListing = techListing;
	}


	public MediaList getMediaList() {
		return mediaList;
	}

	public void setMediaList(MediaList mediaList) {
		this.mediaList = mediaList;
	}

	public AboutAwardRecognition getAboutAwardRecognition() {
		return AboutAwardRecognition;
	}

	public void setAboutAwardRecognition(AboutAwardRecognition aboutAwardRecognition) {
		AboutAwardRecognition = aboutAwardRecognition;
	}

	
	
	

}
