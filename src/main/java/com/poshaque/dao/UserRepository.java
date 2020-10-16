package com.poshaque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poshaque.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
