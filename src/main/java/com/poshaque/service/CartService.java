package com.poshaque.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poshaque.dao.CartRepository;
import com.poshaque.model.Cart;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	public Cart saveCart(Map<String, String> cart, UserPrincipal principal) {
		Cart cartObj = new Cart(Integer.parseInt(cart.get("id")), principal.getId(), cart.get("data"), new Date());
		return cartRepository.save(cartObj);
	}

	public Cart getCart(int userId) {
		return cartRepository.findByUserId(userId);
	}

}
