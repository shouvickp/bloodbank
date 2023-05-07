package com.technichalgarden.bloodbank.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technichalgarden.bloodbank.dto.BloodStockDTO;
import com.technichalgarden.bloodbank.mapper.BloodStockMapper;
import com.technichalgarden.bloodbank.model.BloodStock;
import com.technichalgarden.bloodbank.repository.BloodStockRepository;
import com.technichalgarden.bloodbank.security.JWTService;
import com.technichalgarden.bloodbank.service.BloodStockService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BloodStockServiceImpl implements BloodStockService {

	private static final Logger log = LoggerFactory.getLogger(BloodStockServiceImpl.class);

	@Autowired
	private BloodStockRepository bloodStockRepository;
	@Autowired
	private BloodStockMapper bloodStockMapper;
	@Autowired
	private JWTService jwtService;

	@Override
	public BloodStockDTO addStock(BloodStockDTO bloodStockDTO) {
		log.debug("Inserting Blood Stock for hospital id {} and bloodgroup {}", bloodStockDTO.getHospitalId(),
				bloodStockDTO.getBloodGroup());
		BloodStock bloodStock = bloodStockMapper.toEntity(bloodStockDTO);
		bloodStock.setCreatedBy("Hospital");
		return bloodStockMapper.toDTO(bloodStockRepository.save(bloodStock));
	}

	@Override
	public List<BloodStockDTO> getStocksByHospitalId(Long hospitalId) {
		log.debug("Fetching Blood Stocks detail for hospital {}", hospitalId);
		return bloodStockMapper.toDto(bloodStockRepository.findByHospitalId(hospitalId));
	}

}
