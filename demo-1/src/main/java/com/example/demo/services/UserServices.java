package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.Passwordconfig;
import com.example.demo.dto.loginDTO;
import com.example.demo.dto.registrationDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.Response;
import com.example.demo.utility.EmailSend;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Passwordconfig passwordconfig;
	
	@Autowired
	private EmailSend emailSend;

	/**
	 * @param registrationData
	 * @return
	 */
	
	public Response registration(registrationDTO registrationData) {
		User model = new User();
		model.setEmail(registrationData.getEmail());
		model.setFirstName(registrationData.getFirstName());
		model.setLastName(registrationData.getLastName());
		System.out.println(model.toString() + "services");
		model.setPassword(passwordconfig.getPasswordEncoder().encode(registrationData.getPassword()));
		userRepository.save(model);
	    String email=registrationData.getEmail();
		emailSend.sendMail();
		return new Response(200, "successfully register", true);
	}

	/**
	 * @param loginData
	 * @return
	 */
	
	public Response login(loginDTO loginData) {
		User user = userRepository.findByEmail(loginData.getEmail()).orElse(null);
		if (user != null) {
			boolean b = passwordconfig.getPasswordEncoder().matches(loginData.getPassword(), user.getPassword());
			return new Response(200, "successfully Login", b);
		}
		return new Response(200, "user not found", false);

	}

}
