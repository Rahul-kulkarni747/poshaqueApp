package com.poshaque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poshaque.model.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>{
	
	Cart findByUserId(int userId);
}
