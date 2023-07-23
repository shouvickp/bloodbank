package com.technichalgarden.bloodbank.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.technichalgarden.bloodbank.dto.BloodRequestForOthers;
import com.technichalgarden.bloodbank.dto.BloodRequsetForSelfDTO;
import com.technichalgarden.bloodbank.dto.RequestDTO;
import com.technichalgarden.bloodbank.service.BloodRequestService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class BloodRequestServiceImpl implements BloodRequestService {

	@Override
	public RequestDTO bloodRequestSelf(@Valid BloodRequsetForSelfDTO bloodRequsetForSelfDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDTO bloodRequestOthers(@Valid BloodRequestForOthers bloodRequestForOthers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestDTO> myRequest(Long recieverId) {
		// TODO Auto-generated method stub
		return null;
	}

}
