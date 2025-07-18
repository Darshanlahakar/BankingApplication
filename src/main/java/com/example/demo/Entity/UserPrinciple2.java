package com.example.demo.Entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple2 implements UserDetails {

	private Userss2 userss2;

	public UserPrinciple2(Userss2 userss2) {
		super();
		this.userss2 = userss2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userss2.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userss2.getUsername();
	}

}
