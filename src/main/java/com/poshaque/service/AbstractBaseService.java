package com.poshaque.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.poshaque.model.User;
import com.poshaque.util.ClaimResolver;
import com.poshaque.util.JwtUtil;

public abstract class AbstractBaseService {
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	@Autowired
	private ClaimResolver claimResolver;
	
	protected User loggedInUser;
	
	public static final String PENDING_ACTIVATION = "pending_activation";
	public static final String ACTIVE = "active";
	public static final String INACTIVE = "inactive";
	
	protected User getLoggedInUser(){
		return jwtUtil.getClaimFromToken("", claimResolver);
	}
	
}
