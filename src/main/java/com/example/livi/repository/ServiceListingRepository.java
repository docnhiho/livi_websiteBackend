package com.example.livi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livi.model.ServiceListing;

public interface ServiceListingRepository extends JpaRepository<ServiceListing, Integer> {

}
