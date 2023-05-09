package com.technichalgarden.bloodbank.service;

import java.util.List;

import com.technichalgarden.bloodbank.dto.BloodStockDTO;

import jakarta.validation.Valid;

public interface BloodStockService {
	public BloodStockDTO addStock(BloodStockDTO bloodStockDTO);
	public List<BloodStockDTO> getStocksByHospitalId(Long hospitalId);
	public BloodStockDTO updateStock(@Valid BloodStockDTO bloodStockDTO);
}
