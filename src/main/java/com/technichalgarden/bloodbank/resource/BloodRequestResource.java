package com.technichalgarden.bloodbank.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technichalgarden.bloodbank.dto.BloodRequestForOthers;
import com.technichalgarden.bloodbank.dto.BloodRequsetForSelfDTO;
import com.technichalgarden.bloodbank.dto.HospitalDecisionForRequest;
import com.technichalgarden.bloodbank.dto.RequestDTO;
import com.technichalgarden.bloodbank.security.IsHospital;
import com.technichalgarden.bloodbank.security.IsPatient;
import com.technichalgarden.bloodbank.service.BloodRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class BloodRequestResource {

	private static final Logger log = LoggerFactory.getLogger(BloodRequestResource.class);

	@Autowired
	private BloodRequestService bloodRequestService;
	
	@IsPatient
	@PostMapping("/blood-request/self")
	public ResponseEntity<RequestDTO> bloodRequestSelf(@Valid @RequestBody BloodRequsetForSelfDTO bloodRequsetForSelfDTO) {
		log.info("Start - Request for {} unit  {} Blood from hospital {}", bloodRequsetForSelfDTO.getQuantity(), bloodRequsetForSelfDTO.getBloodGroup(), bloodRequsetForSelfDTO.getHospitalId());
		RequestDTO response = bloodRequestService.bloodRequestSelf(bloodRequsetForSelfDTO);
		log.info("End - Request for {} unit  {} Blood from hospital {}", bloodRequsetForSelfDTO.getQuantity(), bloodRequsetForSelfDTO.getBloodGroup(), bloodRequsetForSelfDTO.getHospitalId());
		return ResponseEntity.ok(response);
	}

	@IsPatient
	@PostMapping("/blood-request/others")
	public ResponseEntity<RequestDTO> bloodRequestOthers(@Valid @RequestBody BloodRequestForOthers bloodRequestForOthers) {
		log.info("Start - Request for {} unit  {} Blood from hospital {}", bloodRequestForOthers.getQuantity(), bloodRequestForOthers.getPatientBloodGroup(), bloodRequestForOthers.getHospitalId());
		RequestDTO response = bloodRequestService.bloodRequestOthers(bloodRequestForOthers);
		log.info("End - Request for {} unit  {} Blood from hospital {}", bloodRequestForOthers.getQuantity(), bloodRequestForOthers.getPatientBloodGroup(), bloodRequestForOthers.getHospitalId());
		return ResponseEntity.ok(response);
	}
	
	@IsPatient
	@GetMapping("/blood-request/reciever/{reciever-id}/my-requests")
	public ResponseEntity<List<RequestDTO>> myRequest(
			@PathVariable(name = "reciever-id", required = true) Long recieverId) {
		log.info("Start - fetching  my request for the reciever {}", recieverId);
		List<RequestDTO> responseList = bloodRequestService.myRequest(recieverId);
		log.info("End - fetching  my request for the reciever {}", recieverId);
		return ResponseEntity.ok(responseList);
	}
	
//	@IsHospital
//	@PostMapping("/blood-request/others")
//	public ResponseEntity<RequestDTO> searchRequest(@Valid @RequestBody BloodRequestForOthers bloodRequestForOthers) {
//		log.info("Start - Adding Blood Stock of blood group {} for Hostpital {}", bloodStockDTO.getBloodGroup(), bloodStockDTO.getHospitalId());
//		return null;
//		log.info("Start - Adding Blood Stock of blood group {} for Hostpital {}", bloodStockDTO.getBloodGroup(), bloodStockDTO.getHospitalId());
//	}
	
	@IsHospital
	@PutMapping("blood-request/save-decision")
	public ResponseEntity<RequestDTO> saveDecision(@Valid @RequestBody HospitalDecisionForRequest hospitalDecision){
		return null;
	}
}
