package com.example.demo.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
@Component
public class EmailSend {
	public SimpleMailMessage sendMail(String email) {
		System.out.println("inside mail");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		       message.setText("send link for verification !!");
		       message.setSubject("Email verification for fundoo account");
		       System.out.println("hello "+message.toString());
		       return message;
		}
	public SimpleMailMessage sendMailReset(String email) {
		System.out.println("inside mail");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		       message.setText("reset link !!");
		       message.setSubject("reset password");
		       System.out.println("http://localhost"+message.toString());
		       return message;
		}
}
