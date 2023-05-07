package com.technichalgarden.bloodbank.service;

import java.io.IOException;

import com.technichalgarden.bloodbank.dto.AuthenticationRequest;
import com.technichalgarden.bloodbank.dto.AuthenticationResponse;
import com.technichalgarden.bloodbank.dto.RegistrationDTO;
import com.technichalgarden.bloodbank.dto.ResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	public ResponseDTO<RegistrationDTO> register(RegistrationDTO request);

	public AuthenticationResponse authenticate(AuthenticationRequest request);

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
