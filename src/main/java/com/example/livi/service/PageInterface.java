package com.example.livi.service;

import java.util.List;

import com.example.livi.model.Page;


public interface PageInterface {
	public Page addPage(Page page);
	
	public Page updatePage(int id, Page page);
	
	public void deletePage(int id);
	
	public List<Page> getAllPage();
	
	public Page getPageById(int id);


}
