package com.ar.darb.userdetails;

import java.util.HashSet;
import java.util.Set;

public class User {

	private int id;
	private String username;
	private String password;
	private boolean enabled;

	private Set<Role> roles = new HashSet<Role>();
	
	public User() {
	}

	public User(int id, String username, String password, boolean enabled, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password +", enabled="
				+ enabled + "]";
	}

}
