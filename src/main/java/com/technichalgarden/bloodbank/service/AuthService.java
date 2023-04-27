package com.technichalgarden.bloodbank.service;

import java.io.IOException;

import com.technichalgarden.bloodbank.dto.AuthenticationRequest;
import com.technichalgarden.bloodbank.dto.AuthenticationResponse;
import com.technichalgarden.bloodbank.dto.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	public AuthenticationResponse register(RegisterRequest request);

	public AuthenticationResponse authenticate(AuthenticationRequest request);

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
