package com.poshaque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poshaque.dto.UserDTO;
import com.poshaque.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hellow from User";
	}
	
	@PostMapping("/signup")
	public boolean signUp(@RequestBody UserDTO dto){
		return userService.signUpUser(dto);
	}
	
	@PostMapping("/check_pin")
	public boolean save(@RequestBody UserDTO dto){
		return userService.saveUser(dto);
	}

}
