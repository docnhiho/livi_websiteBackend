package com.example.livi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livi.model.Page;
import com.example.livi.repository.PageRepository;
import com.example.livi.service.PageInterface;


@Service
public class PageService implements PageInterface {
	@Autowired
	private PageRepository pageRepository;
	
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

	@Override
	public Page addPage(Page page) {
		// TODO Auto-generated method stub
		if(page != null) {
			return pageRepository.save(page);
		}
		return null;
	}

	@Override
	public Page getPageById(int id) {
		// TODO Auto-generated method stub
        return pageRepository.findById(id).orElseThrow(() -> new RuntimeException("Page not found"));
	}
	
	@Override
	public Page updatePage(int id, Page page) {
		Page existingPage = getPageById(id);
        existingPage.setName(page.getName());
        return pageRepository.save(existingPage);
	}
	


	@Override
	public void deletePage(int id) {
		// TODO Auto-generated method stub
        pageRepository.deleteById(id);
	}

	@Override
	public List<Page> getAllPage() {
		// TODO Auto-generated method stub
		return pageRepository.findAll();
	}

}
