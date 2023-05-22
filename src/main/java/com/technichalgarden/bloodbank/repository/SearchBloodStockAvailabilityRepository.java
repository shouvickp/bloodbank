package com.technichalgarden.bloodbank.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;

@Repository
public interface SearchBloodStockAvailabilityRepository {

	public List<BloodStockAvalibiltyInfoDTO> dynamicSearchBloodStockAvalibilityQueryDSL(
			SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO);
}
