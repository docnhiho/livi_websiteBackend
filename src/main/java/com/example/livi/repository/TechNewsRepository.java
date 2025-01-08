package com.example.livi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livi.model.TechNews;

public interface TechNewsRepository extends JpaRepository<TechNews, Integer> {

}
