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
@Table(name = "ClientBrandType")
public class ClientBrandType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "Section_ID", nullable = false)
	private Section session;
	
	@OneToMany(mappedBy = "clientBrandType")
	@JsonIgnore
	public List<ClientBrandList> clientBrandList;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "Note")
	private String note;
	
	@Column(name = "Lang")
	private String lang;

	public ClientBrandType() {
		super();
	}

	public ClientBrandType(int id, Section session, List<ClientBrandList> clientBrandList, String name, String type,
			String note, String lang) {
		super();
		this.id = id;
		this.session = session;
		this.clientBrandList = clientBrandList;
		this.name = name;
		this.type = type;
		this.note = note;
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

	public List<ClientBrandList> getClientBrandList() {
		return clientBrandList;
	}

	public void setClientBrandList(List<ClientBrandList> clientBrandList) {
		this.clientBrandList = clientBrandList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	
}
