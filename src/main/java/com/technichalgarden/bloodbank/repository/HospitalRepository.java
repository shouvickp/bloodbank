package com.technichalgarden.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technichalgarden.bloodbank.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{

}
