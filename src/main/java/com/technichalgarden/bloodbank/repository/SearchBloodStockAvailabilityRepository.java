package com.technichalgarden.bloodbank.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;

@Repository
public interface SearchBloodStockAvailabilityRepository {

	public Page<BloodStockAvalibiltyInfoDTO> dynamicSearchBloodStockAvalibilityQueryDSL(Pageable pageable,
			SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO);
}
