package com.ar.darb.userdetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDB userdb;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userdb.getUserByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("could not find user");
		}
		return new MyUserDetails(user);
	}

}
