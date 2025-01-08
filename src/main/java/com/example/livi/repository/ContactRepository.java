package com.example.livi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livi.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
