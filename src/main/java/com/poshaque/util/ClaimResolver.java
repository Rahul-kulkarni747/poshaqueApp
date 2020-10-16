package com.poshaque.util;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.poshaque.model.User;

import io.jsonwebtoken.Claims;

@Component
public class ClaimResolver implements Function<Claims, User> {

	@Override
	public User apply(Claims t) {
		User user = new User(Integer.parseInt(t.getId()), (String) t.get("firstName"), (String) t.get("lastName"),
				(String) t.get("status"), (Integer) t.get("roleId"), (String) t.get("phone"), (String) t.getSubject(),
				"", "", "");
		return user;
	}

}
