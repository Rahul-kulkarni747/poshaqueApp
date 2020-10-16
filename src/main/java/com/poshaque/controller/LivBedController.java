package com.poshaque.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.service.LivBedService;

@RestController
@RequestMapping("/liv")
public class LivBedController {

	@Autowired
	LivBedService bedService;
	
	@PostMapping("/signup")
	public boolean signup(@RequestBody Map<String,String> dto){
		bedService.saveLivBed(dto);
		return true;
	}
}
