package com.technichalgarden.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.technichalgarden.bloodbank.model.BloodStock;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long>{

	List<BloodStock> findByHospitalId(Long hospitalId);

}
