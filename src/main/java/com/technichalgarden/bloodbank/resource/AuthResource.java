package com.technichalgarden.bloodbank.resource;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technichalgarden.bloodbank.dto.AuthenticationRequest;
import com.technichalgarden.bloodbank.dto.AuthenticationResponse;
import com.technichalgarden.bloodbank.dto.RegistrationDTO;
import com.technichalgarden.bloodbank.dto.ResponseDTO;
import com.technichalgarden.bloodbank.security.LogoutService;
import com.technichalgarden.bloodbank.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthResource {

	private static final Logger log = LoggerFactory.getLogger(AuthResource.class);

	private final AuthService authService;
	private final LogoutService logoutService;

	public AuthResource(AuthService authService, LogoutService logoutService) {
		this.authService = authService;
		this.logoutService = logoutService;
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO<RegistrationDTO>> register(@Valid @RequestBody RegistrationDTO request) {
		log.info("Start - Register new User");
		ResponseDTO<RegistrationDTO> response = authService.register(request);
		log.info("End - Register new User");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@PostMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authService.refreshToken(request, response);
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logoutService.logout(request, response, null);
	}
	
	@GetMapping("/403")
	public ResponseEntity<ResponseDTO> error403(){
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setIsError(true);
		responseDTO.setResponseCode("403");
		responseDTO.setResponseMessage("FullAuthentication is required to access this endpoint");
		return ResponseEntity.ok(responseDTO);
		
	}

}
