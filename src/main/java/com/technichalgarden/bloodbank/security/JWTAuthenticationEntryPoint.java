package com.technichalgarden.bloodbank.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED; // 401

		Map<String, Object> data = new HashMap<>();
		data.put("timestamp", new Date());
		data.put("code", httpStatus.value());
		data.put("status", httpStatus.name());
		data.put("message", authException.getMessage());

		response.setStatus(httpStatus.value());
		response.setContentType("json");
		response.getOutputStream().println(objectMapper.writeValueAsString(data));
	}

}
