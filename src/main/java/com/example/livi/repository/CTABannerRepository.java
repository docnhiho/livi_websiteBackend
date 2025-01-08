package com.example.livi.repository;

import org.springframework.stereotype.Repository;

import com.example.livi.model.CTABanner;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CTABannerRepository extends JpaRepository<CTABanner, Integer> {

}
