package com.example.livi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livi.model.DocumentBanner;
import java.util.List;


public interface DocumentBannerRepository extends JpaRepository<DocumentBanner, Integer>{
	Optional<DocumentBannerRepository> findByAttachement(String attachement);
}
