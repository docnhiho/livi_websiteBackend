package com.example.livi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.livi.model.Page;
import com.example.livi.service.PageService;

@RestController
@RequestMapping("/page")
public class PageController {

	@Autowired
	private PageService pageService;

	@GetMapping("")
	public List<Page> getallPages() {
		return pageService.getAllPage();
	}

	@PostMapping("")
	public Page addPage(@RequestBody Page page) {
		return pageService.addPage(page);
	}

	@GetMapping("/{id}")
	public Page getPageById(@PathVariable int id) {
		return pageService.getPageById(id);
	}

	@PutMapping("/{id}")
	public Page updatePage(@PathVariable int id, @RequestBody Page page) {
		return pageService.updatePage(id, page);
	}

	@DeleteMapping("/{id}")
	public void deletePage(@PathVariable int id) {
		pageService.deletePage(id);
	}

}
