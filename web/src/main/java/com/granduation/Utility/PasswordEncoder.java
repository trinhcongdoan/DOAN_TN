package com.granduation.Utility;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String pass = "Doan123456";
		String encodePass = encoder.encode(pass);
		System.out.println(encodePass);
	}

}
