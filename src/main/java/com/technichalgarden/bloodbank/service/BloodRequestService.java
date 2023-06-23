package com.technichalgarden.bloodbank.service;

import java.util.List;

import com.technichalgarden.bloodbank.dto.BloodRequestForOthers;
import com.technichalgarden.bloodbank.dto.BloodRequsetForSelfDTO;
import com.technichalgarden.bloodbank.dto.RequestDTO;

import jakarta.validation.Valid;

public interface BloodRequestService {

	RequestDTO bloodRequestSelf(@Valid BloodRequsetForSelfDTO bloodRequsetForSelfDTO);

	RequestDTO bloodRequestOthers(@Valid BloodRequestForOthers bloodRequestForOthers);

	List<RequestDTO> myRequest(Long recieverId);

}
