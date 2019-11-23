package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.config.Passwordconfig;
import com.example.demo.dto.forgetPasswordDTO;
import com.example.demo.dto.loginDTO;
import com.example.demo.dto.registrationDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.Response;
import com.example.demo.utility.EmailSend;
import com.example.demo.utility.tokenGenerate;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Passwordconfig passwordconfig;

	@Autowired
	private EmailSend emailSend;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private tokenGenerate tokenUtility;
	/**
	 * @param registrationData
	 * @return
	 */

	public Response registration(registrationDTO registrationData) {
		User user = userRepository.findByEmail(registrationData.getEmail()).orElse(null);
		if (user == null) {
			User model = new User();
			model.setEmail(registrationData.getEmail());
			model.setFirstName(registrationData.getFirstName());
			model.setLastName(registrationData.getLastName());
			System.out.println(model.toString() + "services");
			model.setPassword(passwordconfig.getPasswordEncoder().encode(registrationData.getPassword()));
			userRepository.save(model);
			String token = tokenUtility.createToken(registrationData.getEmail());
			System.out.println("token"+token);
			String email = registrationData.getEmail();
			SimpleMailMessage msg = emailSend.sendMail(email,token);
			sender.send(msg);
			return new Response(200, "successfully register", true);
		}
		return new Response(400, "user already available", false);

	}

	/**
	 * @param loginData
	 * @return
	 */

	public Response login(loginDTO loginData) {
		User user = userRepository.findByEmail(loginData.getEmail()).orElse(null);
		if (user == null) {
			return new Response(400, "user not found", false);
		}

		boolean b = passwordconfig.getPasswordEncoder().matches(loginData.getPassword(), user.getPassword());
		if (!b) {
			return new Response(400, "password does not match", false);
		}
		return new Response(200, "login successfull", true);
	}

	public Response forgetPassword(forgetPasswordDTO forgetPasswordData) {
		User user = userRepository.findByEmail(forgetPasswordData.getEmail()).orElse(null);
		String email = forgetPasswordData.getEmail();
		SimpleMailMessage msg = emailSend.sendMailReset(email);
		sender.send(msg);
		return new Response(200, "successfully mail sent", true);
	}
}
