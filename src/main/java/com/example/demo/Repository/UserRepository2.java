package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Userss2;

public interface UserRepository2 extends JpaRepository<Userss2, Integer> {
	Userss2 findByUsername(String username);
}
