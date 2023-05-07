package com.technichalgarden.bloodbank.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.technichalgarden.bloodbank.model.Token;
import com.technichalgarden.bloodbank.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutService implements LogoutHandler {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		jwt = authHeader.substring(7);
		Optional<Token> storedToken = tokenRepository.findByToken(jwt);
		if (!storedToken.isEmpty()) {
			storedToken.get().setExpired(true);
			storedToken.get().setRevoked(true);
			tokenRepository.save(storedToken.get());
			SecurityContextHolder.clearContext();
		}
	}
}
