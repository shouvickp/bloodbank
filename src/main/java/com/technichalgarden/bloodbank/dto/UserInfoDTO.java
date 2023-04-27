package com.technichalgarden.bloodbank.dto;

import com.technichalgarden.bloodbank.enumeration.Role;

public class UserInfoDTO {

	private String username;

	private Role role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
