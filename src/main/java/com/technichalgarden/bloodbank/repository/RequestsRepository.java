package com.technichalgarden.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technichalgarden.bloodbank.model.Requests;

public interface RequestsRepository extends JpaRepository<Requests, Long>{

}
