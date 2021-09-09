package com.ar.darb.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCreater {

//	public static void main(String[] args) {
//		createPass();
//	}

	private static void createPass() {
		String pass = "user";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPass = encoder.encode(pass);
		System.out.println(encodedPass);
		// $2a$10$L3R7RKqvEN6s6lxHnj7vReCsxP2NLm4nmzabGq.KbXK6l7z55sjeW
	}
}
