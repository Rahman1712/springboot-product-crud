package com.ar.darb.userdetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class UserDB {
	
	public static List<User> USERS_LIST = new ArrayList<>();
	
	Role user = new Role(1, "USER");
	Role editor = new Role(2, "EDITOR");
	Role admin = new Role(3, "ADMIN");
	
	Set<Role> userRole = new HashSet<>(Arrays.asList(user));
	Set<Role> adminRole = new HashSet<>(Arrays.asList(admin));
	Set<Role> editorRole = new HashSet<>(Arrays.asList(editor));
	Set<Role> adminEditorRole = new HashSet<>(Arrays.asList(admin,editor));

	public User getUserByUsername(String username) {
		  return USERS_LIST.stream()
				 .filter(u -> u.getUsername().equals(username))
				 .findAny().orElse(null);
	}
	
	@PostConstruct
	public void listOfUsers(){
		System.out.println("Post construct called");
		
		USERS_LIST.add(new User(1,"user@gmail.com",
				"$2a$10$L3R7RKqvEN6s6lxHnj7vReCsxP2NLm4nmzabGq.KbXK6l7z55sjeW",true, userRole)); //password = user
		USERS_LIST.add(new User(2, "admin@gmail.com",
				"$2a$10$QKo1KymqWsS.Y17XjDysoOnVLVYDt2v3bIcG82qmga/2ZDZE4To8O",true, adminEditorRole));//
		
	}
}
