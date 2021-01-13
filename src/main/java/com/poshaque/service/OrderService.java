package com.poshaque.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poshaque.adapter.RazorPayAdapter;
import com.poshaque.dao.AddressRepository;
import com.poshaque.dao.OrdersRepository;
import com.poshaque.dao.ProductsRepository;
import com.poshaque.exception.PoshaqueBussinessException;
import com.poshaque.model.Orders;

@Service
public class OrderService {

	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	RazorPayAdapter rpAdapter;
	
	@Autowired
	AddressRepository addressRepository;

	public Page<Orders> findCurrentOrdersForUser(UserPrincipal principal, Pageable page) {
		return ordersRepository.findCurrentOrdersForUser(principal.getId(),page);
	}

	public Page<Orders> findOldOrdersForUser(UserPrincipal principal, Pageable page) {
		return ordersRepository.findOldOrdersForUser(principal.getId(),page);
	}

	public boolean cancelOrder(Integer orderId, UserPrincipal principal) {
		Optional<Orders> optOrder = ordersRepository.findById(orderId);
		
		if(!optOrder.isPresent())
			throw new PoshaqueBussinessException("Order does not exist.");
		
		Orders order = optOrder.get();
		
		if(order.getUserId() != principal.getId())
			throw new PoshaqueBussinessException("Unauthorized Access.");
		
		if((order.getCreatedOn().getTime()) > ( new Date().getTime() + (1 * 24 * 60 * 60 * 1000)))
			throw new PoshaqueBussinessException("Order cannot be cancelled.");
			
		JSONObject refundRequest = new JSONObject();
		
		try {
			refundRequest.put("payment_id", order.getClientPaymentId());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ordersRepository.cancelOrder(orderId);
		
		return true;
	}

	public Map<Object, Object> getOrderDetails(Integer orderId, UserPrincipal principal) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Optional<Orders> orders = ordersRepository.findById(orderId);
		Orders order = orders.get();
		
		if(!orders.isPresent())
			throw new PoshaqueBussinessException("Order does not exist.");
		
		if(order.getUserId() != principal.getId())
			throw new PoshaqueBussinessException("Unauthorized Access.");
		
		map.put("order", order);
		Set<Integer> prodIds = new HashSet<Integer>();
		if(order.getOrderDetails() != null){
			String jsonData = order.getOrderDetails();
			try {
				JSONObject cartObj = new JSONObject(jsonData);
				JSONObject prodObj = cartObj.getJSONObject("products");
				Iterator<String> keys = prodObj.keys();
				while(keys.hasNext()) {
				    String key = keys.next();
				    if (prodObj.get(key) instanceof JSONObject) {
				    	prodIds.add(prodObj.getJSONObject(key).getInt("id"));
				    }
				}
			} catch (JSONException e) {
				throw new PoshaqueBussinessException("Invalid cart error.");
			}
		}
		map.put("productThumbnails", productsRepository.findByIdIn(prodIds));
		map.put("selectedAddress", addressRepository.findById(order.getAddressId()).get());
		return map;
	}

}
