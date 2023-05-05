package com.technichalgarden.bloodbank.dto;

import java.io.Serializable;

public class TokenInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String token;
	private long expiry;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiry() {
		return expiry;
	}

	public void setExpiry(long expiry) {
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return "TokenInfo [token=" + token + ", expiry=" + expiry + "]";
	}

}
