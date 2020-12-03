package com.poshaque.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.Cart;
import com.poshaque.service.CartService;
import com.poshaque.service.UserPrincipal;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping
	public Cart saveCart(@RequestBody Map<String,String> cart, @AuthenticationPrincipal UserPrincipal principal){
		return cartService.saveCart(cart,principal);
	}
	
	@GetMapping
	public Cart getCart(@RequestParam int userId){
		return cartService.getCart(userId);
	}
}
