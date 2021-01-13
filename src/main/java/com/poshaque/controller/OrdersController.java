package com.poshaque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.model.Orders;
import com.poshaque.model.ProductThumbnail;
import com.poshaque.service.OrderService;
import com.poshaque.service.UserPrincipal;

@RestController
@RequestMapping("/order")
public class OrdersController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/current")
	public Page<Orders> getCurrentOrders(@AuthenticationPrincipal UserPrincipal principal,@RequestParam(defaultValue = "0") String pageNo,
			@RequestParam(defaultValue = "5") String pageSize,@RequestParam(defaultValue = "") String searchTerm,
			@RequestParam(defaultValue = "created_on") String sortBy, @RequestParam(defaultValue = "DESC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.ASC, sortBy));
		return orderService.findCurrentOrdersForUser(principal,page);
	}

	@GetMapping("/old")
	public Page<Orders> getOldOrders(@AuthenticationPrincipal UserPrincipal principal,@RequestParam(defaultValue = "0") String pageNo,
			@RequestParam(defaultValue = "5") String pageSize,@RequestParam(defaultValue = "") String searchTerm,
			@RequestParam(defaultValue = "created_on") String sortBy, @RequestParam(defaultValue = "DESC") String orderBy){
		Pageable page = orderBy.toUpperCase().equals("DESC")?PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, sortBy)):
			PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(Sort.Direction.ASC, sortBy));
		return orderService.findOldOrdersForUser(principal,page);
	}
	
	@DeleteMapping("/cancel")
	public boolean cancelOrder(@RequestParam("orderId") Integer orderId,@AuthenticationPrincipal UserPrincipal principal){
		return orderService.cancelOrder(orderId,principal);
	}
	
	@GetMapping("/order-details")
	public Map<Object, Object> getOrderDetails(@RequestParam("orderId") Integer orderId,@AuthenticationPrincipal UserPrincipal principal){
		return orderService.getOrderDetails(orderId,principal);
	}
}
