package com.technichalgarden.bloodbank.service;

import java.util.List;

import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.BloodStockDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;

public interface BloodStockService {
	public BloodStockDTO addStock(BloodStockDTO bloodStockDTO);
	public List<BloodStockDTO> getStocksByHospitalId(Long hospitalId);
	public BloodStockDTO updateStock(BloodStockDTO bloodStockDTO);
	public List<BloodStockAvalibiltyInfoDTO> searchBloodStockAvalability(SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO);
}
