package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Userss2;
import com.example.demo.Repository.UserRepository2;
@Service
public class UserService2 {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTService2 jwtService;

	@Autowired
	UserRepository2 userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	UserService2(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public Userss2 addUserss(Userss2 userss) {

		userss.setPassword(bCryptPasswordEncoder.encode(userss.getPassword()));
		return userRepository.save(userss);
	}

	public String login(Userss2 userss) {
		org.springframework.security.core.Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userss.getUsername(), userss.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtService.genrateToken(userss.getUsername());
		}
		return "fail";
	}

}
