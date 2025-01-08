package com.example.livi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livi.model.HeroBanner;


@Repository
public interface HeroBannerRepository extends JpaRepository<HeroBanner, Integer>{

}
