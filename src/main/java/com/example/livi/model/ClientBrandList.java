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
@Table(name = "clientBrandList")
public class ClientBrandList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@ManyToOne
	@JoinColumn(name = "Brand_Type_ID", nullable = false)
	private ClientBrandType clientBrandType;
	
	@Column(name = "Brand_Name")
	private String name;
	
	@Column(name = "Image")
	private String imgae;
	
	@Column(name = "Link")
	private String link;
	
	@Column(name = "Note")
	private String note;
	
	@Column(name = "Lang")
	private String lang;

	public ClientBrandList(int id, Section session, ClientBrandType clientBrandType, String name, String imgae,
			String link, String note, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.clientBrandType = clientBrandType;
		this.name = name;
		this.imgae = imgae;
		this.link = link;
		this.note = note;
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public ClientBrandList() {
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

	public ClientBrandType getClientBrandType() {
		return clientBrandType;
	}

	public void setClientBrandType(ClientBrandType clientBrandType) {
		this.clientBrandType = clientBrandType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgae() {
		return imgae;
	}

	public void setImgae(String imgae) {
		this.imgae = imgae;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
