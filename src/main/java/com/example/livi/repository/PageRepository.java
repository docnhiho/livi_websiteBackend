package com.example.livi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livi.model.Page;


@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

}
