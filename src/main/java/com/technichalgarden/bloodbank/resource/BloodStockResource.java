package com.technichalgarden.bloodbank.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.BloodStockDTO;
import com.technichalgarden.bloodbank.dto.RegistrationDTO;
import com.technichalgarden.bloodbank.dto.ResponseDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;
import com.technichalgarden.bloodbank.security.IsHospital;
import com.technichalgarden.bloodbank.security.IsHospitalPatient;
import com.technichalgarden.bloodbank.security.IsPatient;
import com.technichalgarden.bloodbank.service.AuthService;
import com.technichalgarden.bloodbank.service.BloodStockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class BloodStockResource {
	
	private static final Logger log = LoggerFactory.getLogger(BloodStockResource.class);
	
	private final BloodStockService bloodStockService;

	public BloodStockResource(BloodStockService bloodStockService) {
		this.bloodStockService = bloodStockService;
	}
	
	@IsHospital
	@PostMapping("/blood-stock")
	public ResponseEntity<BloodStockDTO> addBloodStock(@Valid @RequestBody BloodStockDTO bloodStockDTO) {
		log.info("Start - Adding Blood Stock of blood group {} for Hostpital {}", bloodStockDTO.getBloodGroup(), bloodStockDTO.getHospitalId());
		BloodStockDTO bloodStockResponse = bloodStockService.addStock(bloodStockDTO);
		log.info("End - Adding Blood Stock of blood group {} for Hostpital {}", bloodStockDTO.getBloodGroup(), bloodStockDTO.getHospitalId());
		return ResponseEntity.ok(bloodStockResponse);
	}
	
	@IsHospital
	@PutMapping("/blood-stock")
	public ResponseEntity<BloodStockDTO> updateBloodStock(@Valid @RequestBody BloodStockDTO bloodStockDTO) {
		log.info("Start - Update Blood Stock of blood group {} for Hostpital {}", bloodStockDTO.getBloodGroup(), bloodStockDTO.getHospitalId());
		BloodStockDTO bloodStockResponse = bloodStockService.updateStock(bloodStockDTO);
		log.info("End - Update Blood Stock of blood group {} for Hostpital {}", bloodStockDTO.getBloodGroup(), bloodStockDTO.getHospitalId());
		return ResponseEntity.ok(bloodStockResponse);
	}
	
	@IsHospitalPatient
	@GetMapping("/hospital/{hospitalId}/blood-stocks")
	public ResponseEntity<List<BloodStockDTO>> getBloodStocksByHospital(@PathVariable(value = "hospitalId", required=true) Long hospitalId) {
		log.info("Start - get Blood Stock details for Hostpital {}", hospitalId);
		List<BloodStockDTO> bloodStocks = bloodStockService.getStocksByHospitalId(hospitalId);
		log.info("End - get Blood Stock details for Hostpital {}", hospitalId);
		return ResponseEntity.ok(bloodStocks);
	}
	
	@IsPatient
	@PostMapping("/blood-stock/search-availability")
	public ResponseEntity<List<BloodStockAvalibiltyInfoDTO>> searchAvailableBloodStock(
			@RequestBody SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO) {
		log.info("Start - Search Available Blood Stock : {}", searchBloodStockAvalabilityDTO);
		List<BloodStockAvalibiltyInfoDTO> bloodStockAvaliabilityInfos = bloodStockService.searchBloodStockAvalability(searchBloodStockAvalabilityDTO);
		log.info("End - Search Available Blood Stock : {}", searchBloodStockAvalabilityDTO);
		return ResponseEntity.ok(bloodStockAvaliabilityInfos);
	}

}
