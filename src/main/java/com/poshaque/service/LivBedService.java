package com.poshaque.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poshaque.dao.LivBedRepository;
import com.poshaque.model.Signuplivbed;

@Service
public class LivBedService {

	@Autowired
	LivBedRepository bedRepository;

	@Autowired
	private EmailServiceImpl emailService;
	
	public void saveLivBed(Map<String, String> map){
		bedRepository.save(new Signuplivbed(map.get("fname"), map.get("lname"), map.get("email"), map.get("phone"), map.get("locations"), map.get("type"), map.get("owns")));
	}
}
