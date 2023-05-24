package com.technichalgarden.bloodbank.service;

import java.util.List;

import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.BloodStockDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BloodStockService {
	public BloodStockDTO addStock(BloodStockDTO bloodStockDTO);
	public List<BloodStockDTO> getStocksByHospitalId(Long hospitalId);
	public BloodStockDTO updateStock(BloodStockDTO bloodStockDTO);
	public Page<BloodStockAvalibiltyInfoDTO> searchBloodStockAvalability(Pageable pageable, SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO);
}
