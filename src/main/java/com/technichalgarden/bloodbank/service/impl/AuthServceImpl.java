package com.technichalgarden.bloodbank.service.impl;

import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technichalgarden.bloodbank.component.SeriesGenerator;
import com.technichalgarden.bloodbank.dto.AuthenticationRequest;
import com.technichalgarden.bloodbank.dto.AuthenticationResponse;
import com.technichalgarden.bloodbank.dto.RegisterRequest;
import com.technichalgarden.bloodbank.dto.RegistrationDTO;
import com.technichalgarden.bloodbank.dto.ResponseDTO;
import com.technichalgarden.bloodbank.dto.TokenInfo;
import com.technichalgarden.bloodbank.dto.UserInfoDTO;
import com.technichalgarden.bloodbank.enumeration.Role;
import com.technichalgarden.bloodbank.enumeration.TokenType;
import com.technichalgarden.bloodbank.enumeration.UserType;
import com.technichalgarden.bloodbank.exception.BadRequestException;
import com.technichalgarden.bloodbank.model.Hospital;
import com.technichalgarden.bloodbank.model.Reciever;
import com.technichalgarden.bloodbank.model.Token;
import com.technichalgarden.bloodbank.model.User;
import com.technichalgarden.bloodbank.repository.HospitalRepository;
import com.technichalgarden.bloodbank.repository.RecieverRepository;
import com.technichalgarden.bloodbank.repository.TokenRepository;
import com.technichalgarden.bloodbank.repository.UserRepository;
import com.technichalgarden.bloodbank.resource.AuthResource;
import com.technichalgarden.bloodbank.security.JWTService;
import com.technichalgarden.bloodbank.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServceImpl implements AuthService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthServceImpl.class);

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	private final SeriesGenerator seriesGenerator;
	private final HospitalRepository hospitalRepository;
	private final RecieverRepository recieverRepository;

	private static final EnumMap<UserType, Role> userTypeToRoleMap = new EnumMap<>(UserType.class) {
		private static final long serialVersionUID = 1L;
		{
			put(UserType.A, Role.ADMIN);
			put(UserType.D, Role.DONER);
			put(UserType.H, Role.BLOOD_BANK);
			put(UserType.R, Role.RECIEVER);
		}
	};

	public AuthServceImpl(UserRepository userRepository, TokenRepository tokenRepository,
			PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager,
			SeriesGenerator seriesGenerator, HospitalRepository hospitalRepository,
			RecieverRepository recieverRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.seriesGenerator = seriesGenerator;
		this.hospitalRepository = hospitalRepository;
		this.recieverRepository = recieverRepository;
	}

	@Override
	public ResponseDTO<RegistrationDTO> register(RegistrationDTO request) {
		log.debug("Request for new user registration {}", request);
		log.debug("Genrating username for userType {}", request.getUserType().name());
		String loginName = request.getUserType().name() + seriesGenerator.getSeries(request.getUserType().name());
		log.debug("Saving basic user info to user table - start");
		User user = new User().username(loginName).password(passwordEncoder.encode(request.getPassword()))
				.role(userTypeToRoleMap.get(request.getUserType())).enabled(false);
		userRepository.save(user);
		log.debug("Saving basic user info to user table - end");
		switch (request.getUserType().name()) {
		case "H":
			log.debug("Saving hospital info to hospital table - start");
			Hospital hospital = new Hospital();
			hospital.setLoginName(loginName);
			hospital.setHospitalName(request.getName());
			hospital.setContactNumber(request.getContactNumber());
			hospital.setEmail(request.getEmail());
			hospital.setAddressLine(request.getAddressLine());
			hospital.setCity(request.getCity());
			hospital.setState(request.getState());
			hospital.setPincode(request.getPincode());
			hospitalRepository.save(hospital);
			log.debug("Saving hospital info to hospital table - end");
			request.setLoginName(loginName);
			return new ResponseDTO<>("Your registration is Successfully completed, User Name is " + loginName
					+ ". Please login with these user name and you password.", "200", false, request);
		case "R":
			log.debug("Saving reciever info to reciever table - start");
			Reciever reciever = new Reciever();
			reciever.setLoginName(loginName);
			reciever.setName(request.getName());
			reciever.setMobileNo(request.getContactNumber());
			reciever.setEmail(request.getEmail());
			reciever.setAddressLine(request.getAddressLine());
			reciever.setCity(request.getCity());
			reciever.setState(request.getState());
			reciever.setPincode(request.getPincode());
			recieverRepository.save(reciever);
			log.debug("Saving reciever info to reciever table - start");
			request.setLoginName(loginName);
			return new ResponseDTO<>("Your registration is Successfully completed, User Name is " + loginName
					+ ". Please login with these user name and you password.", "200", false, request);
		default:
			throw new BadRequestException("Invalid user type  provided");
		}
//		TokenInfo jwtToken = jwtService.generateToken(user.getRole(), user.getUsername());
//		TokenInfo refreshToken = jwtService.generateRefreshToken(user.getRole(), user.getUsername());
//		saveUserToken(user, jwtToken.getToken());
//		return new AuthenticationResponse().accessToken(jwtToken.getToken()).expiresIn(jwtToken.getExpiry())
//				.refreshToken(refreshToken.getToken()).refreshExpiresIn(refreshToken.getExpiry());
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		TokenInfo jwtToken = jwtService.generateToken(user.getRole(), user.getUsername());
		TokenInfo refreshToken = jwtService.generateRefreshToken(user.getRole(), user.getUsername());
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken.getToken());
		return new AuthenticationResponse().accessToken(jwtToken.getToken()).expiresIn(jwtToken.getExpiry())
				.refreshToken(refreshToken.getToken()).refreshExpiresIn(refreshToken.getExpiry());
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
				TokenInfo accessToken = jwtService.generateToken(user.getRole(), user.getUsername());
				long refreshTokenExpiry = jwtService.getRefreshExpiration(refreshToken);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken.getToken());
				AuthenticationResponse authResponse = new AuthenticationResponse().accessToken(accessToken.getToken())
						.expiresIn(accessToken.getExpiry()).refreshToken(refreshToken)
						.refreshExpiresIn(refreshTokenExpiry);
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}
}
