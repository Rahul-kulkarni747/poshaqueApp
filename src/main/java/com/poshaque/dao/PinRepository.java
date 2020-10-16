package com.poshaque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poshaque.model.Pin;

public interface PinRepository extends JpaRepository<Pin, Integer> {

	Pin findByUserEmail(String userEmail);
}
