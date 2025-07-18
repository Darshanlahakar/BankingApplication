package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserPrinciple2;
import com.example.demo.Entity.Userss2;
import com.example.demo.Repository.UserRepository2;
@Service
public class MyUserDetailsService2 implements UserDetailsService {

	@Autowired
	private UserRepository2 userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userss2 user = userRepository.findByUsername(username);
		if (user == null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}

		return new UserPrinciple2(user);
	}
}
