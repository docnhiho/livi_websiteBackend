package com.example.livi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.livi.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
