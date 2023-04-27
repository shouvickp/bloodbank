package com.technichalgarden.bloodbank.service.impl;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technichalgarden.bloodbank.dto.AuthenticationRequest;
import com.technichalgarden.bloodbank.dto.AuthenticationResponse;
import com.technichalgarden.bloodbank.dto.RegisterRequest;
import com.technichalgarden.bloodbank.dto.UserInfoDTO;
import com.technichalgarden.bloodbank.enumeration.TokenType;
import com.technichalgarden.bloodbank.model.Token;
import com.technichalgarden.bloodbank.model.User;
import com.technichalgarden.bloodbank.repository.TokenRepository;
import com.technichalgarden.bloodbank.repository.UserRepository;
import com.technichalgarden.bloodbank.security.JWTService;
import com.technichalgarden.bloodbank.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthServceImpl implements AuthService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;

	private final PasswordEncoder passwordEncoder;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthServceImpl(UserRepository userRepository, TokenRepository tokenRepository,
			PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole()).enabled(false);
		user = userRepository.save(user);
		String jwtToken = jwtService.generateToken(user.getRole(), user.getUsername());
		String refreshToken = jwtService.generateRefreshToken(user.getRole(), user.getUsername());
		saveUserToken(user, jwtToken);
		return new AuthenticationResponse().accessToken(jwtToken).refreshToken(refreshToken);
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		String jwtToken = jwtService.generateToken(user.getRole(), user.getUsername());
		String refreshToken = jwtService.generateRefreshToken(user.getRole(), user.getUsername());
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return new AuthenticationResponse().accessToken(jwtToken).refreshToken(refreshToken);
	}

	private void saveUserToken(User user, String jwtToken) {
		Token token = new Token().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false);
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final UserInfoDTO userInfo;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userInfo = jwtService.getUserInfo(refreshToken);
		if (userInfo.getUsername() != null) {
			User user = userRepository.findByUsername(userInfo.getUsername())
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user.getRole(), user.getUsername());
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				AuthenticationResponse authResponse = new AuthenticationResponse().accessToken(accessToken)
						.refreshToken(refreshToken);
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}
}
