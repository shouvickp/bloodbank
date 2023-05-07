package com.technichalgarden.bloodbank.service;

import java.util.List;

import com.technichalgarden.bloodbank.dto.BloodStockDTO;

public interface BloodStockService {
	public BloodStockDTO addStock(BloodStockDTO bloodStockDTO);
	public List<BloodStockDTO> getStocksByHospitalId(Long hospitalId);
}
