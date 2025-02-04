package com.example.livi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livi.model.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

}
