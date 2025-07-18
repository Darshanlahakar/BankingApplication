package com.example.demo.Controller;

import com.example.demo.Services.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Entity.Userss2;

@RestController
public class UserController2 {

	private final UserService2 userService2;

	@Autowired
	UserService2 usrService2;

	UserController2(UserService2 userService2) {
		this.userService2 = userService2;
	}

	@PostMapping("register")
	public ResponseEntity<Userss2> adduser(@RequestBody Userss2 usr) {
		return ResponseEntity.ok(usrService2.addUserss(usr));

	}

	@PostMapping("/login3")
	public String login(@RequestBody Userss2 userss) {
		System.out.println("Loginnnnnnnnnnn");
		return userService2.login(userss);
	}
	
	@GetMapping("/healthChecker")
	public String healhChecker() {
		return "Application is up";
	}
}
