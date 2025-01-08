package com.example.livi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livi.model.TechListing;

@Repository
public interface TechListingRepository extends JpaRepository<TechListing, Integer> {

}
