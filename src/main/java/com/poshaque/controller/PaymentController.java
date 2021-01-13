package com.poshaque.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.Orders;
import com.poshaque.service.PaymentService;
import com.poshaque.service.UserPrincipal;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/orders")
	public Map<?, ?> createOrder(@Param("addressId") int addressId,@AuthenticationPrincipal UserPrincipal principal){	
		return paymentService.createRPOrder(principal,addressId);
	}
	
	@PostMapping("/orders")
	public Orders updateOrder(@RequestBody Map<String,Object> map,@AuthenticationPrincipal UserPrincipal principal){	
		return paymentService.updateOrder(map,principal);
	}
}
