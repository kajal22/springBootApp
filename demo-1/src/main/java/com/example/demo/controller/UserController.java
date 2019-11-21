package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.loginDTO;
import com.example.demo.dto.registrationDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.Response;
import com.example.demo.services.UserServices;


@RestController
public class UserController {
	
   @Autowired
	private UserServices userServices;
   
   
	@PostMapping("/registration")
	public Response registration(@RequestBody registrationDTO registrationData) {
		System.out.println("into registration");
	return userServices.registration(registrationData);
	
}
	@PostMapping("/login")
	public Response login(@RequestBody loginDTO loginData) {
		System.out.println("into login");
	return userServices.login(loginData);
	
}	
	
}
