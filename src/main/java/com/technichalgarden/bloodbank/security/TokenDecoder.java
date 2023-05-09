package com.technichalgarden.bloodbank.security;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.technichalgarden.bloodbank.dto.UserInfoDTO;
import com.technichalgarden.bloodbank.model.User;

@Component
public class TokenDecoder {

	public UserInfoDTO fetchUserInfoDTO() {
		UserInfoDTO userInfoDTO = null;
		Optional<User> currentUser = getCurrentUser();
		if (currentUser.isPresent()) {
			userInfoDTO = new UserInfoDTO();
			userInfoDTO.setUsername(currentUser.get().getUsername());
			userInfoDTO.setRole(currentUser.get().getRole());
		}
		return userInfoDTO;
	}

	private Optional<User> getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}
		return Optional.ofNullable((User) authentication.getPrincipal());
	}

}
