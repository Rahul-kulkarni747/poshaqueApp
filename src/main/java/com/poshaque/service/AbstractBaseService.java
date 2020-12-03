package com.poshaque.service;

import com.poshaque.model.User;

public abstract class AbstractBaseService {
	
	protected User loggedInUser;
	
	public static final String PENDING_ACTIVATION = "pending_activation";
	public static final String ACTIVE = "active";
	public static final String INACTIVE = "inactive";
	
}
