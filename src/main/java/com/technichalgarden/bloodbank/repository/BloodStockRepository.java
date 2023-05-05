package com.technichalgarden.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technichalgarden.bloodbank.model.BloodStock;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long>{

}
