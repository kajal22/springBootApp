package com.example.demo.utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class tokenGenerate {
	public String createToken(String email) {

		return Jwts.builder().setSubject(email)
				.signWith(SignatureAlgorithm.HS512, "secret")
				.compact();
	}
}
