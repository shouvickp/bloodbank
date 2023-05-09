package com.technichalgarden.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.technichalgarden.bloodbank.enumeration.BloodGroup;
import com.technichalgarden.bloodbank.model.BloodStock;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long>{

	List<BloodStock> findByHospitalId(Long hospitalId);

	Optional<BloodStock> findByHospitalIdAndBloodGroupAndCreatedBy(Long hospitalId, BloodGroup bloodGroup, String user);

}
