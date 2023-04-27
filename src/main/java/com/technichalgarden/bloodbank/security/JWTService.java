package com.technichalgarden.bloodbank.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.technichalgarden.bloodbank.dto.UserInfoDTO;
import com.technichalgarden.bloodbank.enumeration.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;

	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;

	@Value("${application.security.jwt.refresh-token.expiration}")
	private long refreshExpiration;

	public String generateToken(Role role, String username) {
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("role", role.name());
		return buildToken(extraClaims, username, jwtExpiration);
	}

	public String generateRefreshToken(Role role, String username) {
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("role", role.name());
		return buildToken(extraClaims, username, refreshExpiration);
	}

	public UserInfoDTO getUserInfo(String token) {
		final Claims claims = getAllClaims(token);
		UserInfoDTO userinfo = new UserInfoDTO();
		userinfo.setUsername(claims.getSubject());
		userinfo.setRole(Role.valueOf(claims.get("role").toString()));
		return userinfo;
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final Claims claims = getAllClaims(token);
		return (claims.getSubject().equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private String buildToken(Map<String, Object> extraClaims, String username, long expiration) {
		return Jwts.builder().setClaims(extraClaims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	private Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private boolean isTokenExpired(String token) {
		final Claims claims = getAllClaims(token);
		return claims.getExpiration().before(new Date());
	}

}
