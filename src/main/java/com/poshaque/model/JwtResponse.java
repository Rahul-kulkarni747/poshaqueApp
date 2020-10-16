package com.poshaque.model;

import com.poshaque.service.UserPrincipal;

public class JwtResponse {

	private final String jwttoken;
	private final UserPrincipal user;

	public JwtResponse(String jwttoken,UserPrincipal user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public UserPrincipal getUser(){
		return this.user;
	}
}
