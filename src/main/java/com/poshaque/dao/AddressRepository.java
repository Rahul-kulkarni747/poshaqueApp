package com.poshaque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poshaque.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
