package com.example.livi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livi.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
