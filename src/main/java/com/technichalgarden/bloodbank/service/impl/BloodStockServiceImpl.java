package com.technichalgarden.bloodbank.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.BloodStockDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;
import com.technichalgarden.bloodbank.dto.UserInfoDTO;
import com.technichalgarden.bloodbank.exception.ResourceNotFoundException;
import com.technichalgarden.bloodbank.mapper.BloodStockMapper;
import com.technichalgarden.bloodbank.model.BloodStock;
import com.technichalgarden.bloodbank.repository.BloodStockRepository;
import com.technichalgarden.bloodbank.security.TokenDecoder;
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
	private TokenDecoder tokenDecoder;

	@Override
	public BloodStockDTO addStock(BloodStockDTO bloodStockDTO) {
		log.debug("Inserting Blood Stock for hospital id {} and bloodgroup {}", bloodStockDTO.getHospitalId(),
				bloodStockDTO.getBloodGroup());
		BloodStock bloodStock = bloodStockMapper.toEntity(bloodStockDTO);
		return bloodStockMapper.toDTO(bloodStockRepository.save(bloodStock));
	}
	
	@Override
	public BloodStockDTO updateStock(BloodStockDTO bloodStockDTO) {
		log.debug("Updating Blood Stock for hospital id {} and bloodgroup {}", bloodStockDTO.getHospitalId(),
				bloodStockDTO.getBloodGroup());
		UserInfoDTO userInfoDTO = tokenDecoder.fetchUserInfoDTO();
		BloodStock bloodStock = bloodStockRepository.findByHospitalIdAndBloodGroupAndCreatedBy(bloodStockDTO.getHospitalId(),
				bloodStockDTO.getBloodGroup(), userInfoDTO.getUsername()).orElseThrow(()-> new ResourceNotFoundException("No Record Found for thsis hospital"));
		bloodStock.setStockCount(bloodStockDTO.getStockCount());
		return bloodStockMapper.toDTO(bloodStockRepository.save(bloodStock));
	}

	@Override
	public List<BloodStockDTO> getStocksByHospitalId(Long hospitalId) {
		log.debug("Fetching Blood Stocks detail for hospital {}", hospitalId);
		return bloodStockMapper.toDto(bloodStockRepository.findByHospitalId(hospitalId));
	}

	@Override
	public Page<BloodStockAvalibiltyInfoDTO> searchBloodStockAvalability(Pageable pageable,
			SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO) {
		Page<BloodStockAvalibiltyInfoDTO>  responsePage = bloodStockRepository.dynamicSearchBloodStockAvalibilityQueryDSL(pageable, searchBloodStockAvalabilityDTO);
		return responsePage;
	}

}
