package com.poshaque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.dto.AddressDTO;
import com.poshaque.model.Address;
import com.poshaque.service.AddressService;
import com.poshaque.service.UserPrincipal;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addrService;
	
	@GetMapping
	public List<Address> getAllUserAddress(@AuthenticationPrincipal UserPrincipal principal){
		return addrService.getAllAddress(principal);
	}
	
	@PostMapping
	public Address saveAddress(@RequestBody AddressDTO address,@AuthenticationPrincipal UserPrincipal principal){
		return addrService.saveAddress(address,principal);
	}
	
	@DeleteMapping
	public boolean deleteAddress(@RequestParam("id") int addressId ){
		return addrService.deleteAddress(addressId);
	}
}
