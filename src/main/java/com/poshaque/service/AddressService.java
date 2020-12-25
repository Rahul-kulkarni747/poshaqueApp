package com.poshaque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poshaque.dao.AddressRepository;
import com.poshaque.dto.AddressDTO;
import com.poshaque.model.Address;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	public List<Address> getAllAddress(UserPrincipal principal){
		return addressRepo.findByUserId(principal.getId());
	}

	public Address saveAddress(AddressDTO addressDTO,UserPrincipal principal) {
		Address address = new Address(addressDTO.getAddressLine1(), addressDTO.getAddressLine2(),
				addressDTO.getState(), addressDTO.getCity(), addressDTO.getZip(), addressDTO.getLandmark(),
				principal.getId());
		int id =  addressDTO.getId() == null? 0: addressDTO.getId();
		address.setId(id);
		return addressRepo.save(address);
	}

	public boolean deleteAddress(int addressId) {
		addressRepo.deleteById(addressId);
		return true;
	}
}
