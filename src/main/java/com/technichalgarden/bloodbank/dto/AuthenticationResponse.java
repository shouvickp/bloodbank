package com.technichalgarden.bloodbank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "access_token", "expires_in", "token_type", "refresh_token", "refresh_expires_in" })
public class AuthenticationResponse {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private Long expiresIn;
	@JsonProperty("refresh_expires_in")
	private Long refreshExpiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public AuthenticationResponse accessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public AuthenticationResponse refreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public AuthenticationResponse tokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public AuthenticationResponse expiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Long getRefreshExpiresIn() {
		return refreshExpiresIn;
	}

	public AuthenticationResponse refreshExpiresIn(Long refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
		return this;
	}

	public void setRefreshExpiresIn(Long refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}

}
